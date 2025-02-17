package com.course.avro.tutorial;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import com.course.avro.data.Avro02;

public class Avro02App {

	public static void main(String[] args) {
		var file = new File("avro02.avro");
		write(file);
		System.out.println("");
		read(file);
	}

	private static void write(File toFile) {
		var data = Avro02.newBuilder().setMyDecimal(new BigDecimal(10472.281474693).setScale(5, RoundingMode.HALF_UP))
				.setMyUUID(UUID.randomUUID()).setMyDate(LocalDate.now().withMonth(12).withDayOfMonth(31))
				.setMyTimeMillis(LocalTime.now()).setMyTimestampMillis(Instant.now()).build();

		var datumWriter = new SpecificDatumWriter<>(Avro02.class);

		try (var dataWriter = new DataFileWriter<>(datumWriter)) {
			dataWriter.create(data.getSchema(), toFile);
			dataWriter.append(data);

			System.out.println("Writing");
			System.out.println(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void read(File fromFile) {
		var datumReader = new SpecificDatumReader<>(Avro02.class);

		System.out.println("Reading");
		try (var dataReader = new DataFileReader<>(fromFile, datumReader)) {
			dataReader.forEach(data -> {
				System.out.println("Decimal is " + data.getMyDecimal());
				System.out.println("UUID is " + data.getMyUUID());
				System.out.println("Date is " + data.getMyDate());
				System.out.println("Time is " + data.getMyTimeMillis());
				System.out.println("Timestamp is " + data.getMyTimestampMillis());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
