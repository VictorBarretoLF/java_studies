package com.course.kafka.broker.stream.web;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Printed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.course.kafka.broker.message.WebColorVoteMessage;
import com.course.kafka.broker.message.WebDesignVoteMessage;
import com.course.kafka.broker.message.WebLayoutVoteMessage;
import com.course.kafka.util.WebColorVoteTimestampExtractor;
import com.course.kafka.util.WebLayoutVoteTimestampExtractor;

// @Component
public class WebDesignVoteTwoStream {

    private WebDesignVoteMessage voteJoiner(String color, String layout) {
        var result = new WebDesignVoteMessage();

        result.setColor(color);
        result.setLayout(layout);

        return result;
    }

    @Autowired
    void kstreamWebDesignVote(StreamsBuilder builder) {
        var stringSerde = Serdes.String();
        var colorSerde = new JsonSerde<>(WebColorVoteMessage.class);
        var layoutSerde = new JsonSerde<>(WebLayoutVoteMessage.class);
        var designSerde = new JsonSerde<>(WebDesignVoteMessage.class);

        var colorTable = builder.stream("t-commodity-web-vote-color",
                Consumed.with(stringSerde, colorSerde, new WebColorVoteTimestampExtractor(), null))
                .mapValues(
                        v -> v.getColor())
                .toTable();

        var layoutTable = builder.stream("t-commodity-web-vote-layout",
                Consumed.with(stringSerde, layoutSerde, new WebLayoutVoteTimestampExtractor(), null))
                .mapValues(
                        v -> v.getLayout())
                .toTable();

        var joinTable = colorTable.leftJoin(layoutTable, this::voteJoiner, Materialized.with(stringSerde, designSerde));

        joinTable.toStream().to("t-commodity-web-vote-two-result");

        joinTable.groupBy(
                (username, votedDesign) -> KeyValue.pair(votedDesign.getColor(), votedDesign.getColor())).count()
                .toStream().print(Printed.<String, Long>toSysOut().withLabel("Vote two - color"));

        joinTable.groupBy(
                (username, votedDesign) -> KeyValue.pair(votedDesign.getLayout(), votedDesign.getLayout())).count()
                .toStream().print(Printed.<String, Long>toSysOut().withLabel("Vote two - layout"));
    }

}
