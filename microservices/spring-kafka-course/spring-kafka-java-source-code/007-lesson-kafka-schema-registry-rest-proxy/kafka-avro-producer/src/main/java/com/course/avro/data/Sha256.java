/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.course.avro.data;
@org.apache.avro.specific.FixedSize(32)
@org.apache.avro.specific.AvroGenerated
public class Sha256 extends org.apache.avro.specific.SpecificFixed {
  private static final long serialVersionUID = 4286785336041960821L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"fixed\",\"name\":\"Sha256\",\"namespace\":\"com.course.avro.data\",\"size\":32}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  /** Creates a new Sha256 */
  public Sha256() {
    super();
  }

  /**
   * Creates a new Sha256 with the given bytes.
   * @param bytes The bytes to create the new Sha256.
   */
  public Sha256(byte[] bytes) {
    super(bytes);
  }

  private static final org.apache.avro.io.DatumWriter<Sha256>
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter<Sha256>(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, org.apache.avro.specific.SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader<Sha256>
    READER$ = new org.apache.avro.specific.SpecificDatumReader<Sha256>(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, org.apache.avro.specific.SpecificData.getDecoder(in));
  }

}
