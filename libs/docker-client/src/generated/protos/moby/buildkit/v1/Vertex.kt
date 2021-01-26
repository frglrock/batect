// Code generated by Wire protocol buffer compiler, do not edit.
// Source: moby.buildkit.v1.Vertex in github.com/moby/buildkit/api/services/control/control.proto
package moby.buildkit.v1

import com.squareup.wire.FieldEncoding
import com.squareup.wire.Instant
import com.squareup.wire.Message
import com.squareup.wire.ProtoAdapter
import com.squareup.wire.ProtoReader
import com.squareup.wire.ProtoWriter
import com.squareup.wire.Syntax.PROTO_3
import com.squareup.wire.WireField
import com.squareup.wire.`internal`.immutableCopyOf
import com.squareup.wire.`internal`.sanitize
import kotlin.Any
import kotlin.AssertionError
import kotlin.Boolean
import kotlin.Deprecated
import kotlin.DeprecationLevel
import kotlin.Int
import kotlin.Long
import kotlin.Nothing
import kotlin.String
import kotlin.Unit
import kotlin.collections.List
import kotlin.hashCode
import kotlin.jvm.JvmField
import okio.ByteString

public class Vertex(
  @field:WireField(
    tag = 1,
    adapter = "com.squareup.wire.ProtoAdapter#STRING",
    label = WireField.Label.OMIT_IDENTITY
  )
  public val digest: String = "",
  inputs: List<String> = emptyList(),
  @field:WireField(
    tag = 3,
    adapter = "com.squareup.wire.ProtoAdapter#STRING",
    label = WireField.Label.OMIT_IDENTITY
  )
  public val name: String = "",
  @field:WireField(
    tag = 4,
    adapter = "com.squareup.wire.ProtoAdapter#BOOL",
    label = WireField.Label.OMIT_IDENTITY
  )
  public val cached: Boolean = false,
  @field:WireField(
    tag = 5,
    adapter = "com.squareup.wire.ProtoAdapter#INSTANT",
    label = WireField.Label.OMIT_IDENTITY
  )
  public val started: Instant? = null,
  @field:WireField(
    tag = 6,
    adapter = "com.squareup.wire.ProtoAdapter#INSTANT",
    label = WireField.Label.OMIT_IDENTITY
  )
  public val completed: Instant? = null,
  /**
   * typed errors?
   */
  @field:WireField(
    tag = 7,
    adapter = "com.squareup.wire.ProtoAdapter#STRING",
    label = WireField.Label.OMIT_IDENTITY
  )
  public val error: String = "",
  unknownFields: ByteString = ByteString.EMPTY
) : Message<Vertex, Nothing>(ADAPTER, unknownFields) {
  @field:WireField(
    tag = 2,
    adapter = "com.squareup.wire.ProtoAdapter#STRING",
    label = WireField.Label.REPEATED
  )
  public val inputs: List<String> = immutableCopyOf("inputs", inputs)

  @Deprecated(
    message = "Shouldn't be used in Kotlin",
    level = DeprecationLevel.HIDDEN
  )
  public override fun newBuilder(): Nothing = throw AssertionError()

  public override fun equals(other: Any?): Boolean {
    if (other === this) return true
    if (other !is Vertex) return false
    if (unknownFields != other.unknownFields) return false
    if (digest != other.digest) return false
    if (inputs != other.inputs) return false
    if (name != other.name) return false
    if (cached != other.cached) return false
    if (started != other.started) return false
    if (completed != other.completed) return false
    if (error != other.error) return false
    return true
  }

  public override fun hashCode(): Int {
    var result = super.hashCode
    if (result == 0) {
      result = unknownFields.hashCode()
      result = result * 37 + digest.hashCode()
      result = result * 37 + inputs.hashCode()
      result = result * 37 + name.hashCode()
      result = result * 37 + cached.hashCode()
      result = result * 37 + started.hashCode()
      result = result * 37 + completed.hashCode()
      result = result * 37 + error.hashCode()
      super.hashCode = result
    }
    return result
  }

  public override fun toString(): String {
    val result = mutableListOf<String>()
    result += """digest=${sanitize(digest)}"""
    if (inputs.isNotEmpty()) result += """inputs=${sanitize(inputs)}"""
    result += """name=${sanitize(name)}"""
    result += """cached=$cached"""
    if (started != null) result += """started=$started"""
    if (completed != null) result += """completed=$completed"""
    result += """error=${sanitize(error)}"""
    return result.joinToString(prefix = "Vertex{", separator = ", ", postfix = "}")
  }

  public fun copy(
    digest: String = this.digest,
    inputs: List<String> = this.inputs,
    name: String = this.name,
    cached: Boolean = this.cached,
    started: Instant? = this.started,
    completed: Instant? = this.completed,
    error: String = this.error,
    unknownFields: ByteString = this.unknownFields
  ): Vertex = Vertex(digest, inputs, name, cached, started, completed, error, unknownFields)

  public companion object {
    @JvmField
    public val ADAPTER: ProtoAdapter<Vertex> = object : ProtoAdapter<Vertex>(
      FieldEncoding.LENGTH_DELIMITED, 
      Vertex::class, 
      "type.googleapis.com/moby.buildkit.v1.Vertex", 
      PROTO_3, 
      null
    ) {
      public override fun encodedSize(value: Vertex): Int {
        var size = value.unknownFields.size
        if (value.digest != "") size += ProtoAdapter.STRING.encodedSizeWithTag(1, value.digest)
        size += ProtoAdapter.STRING.asRepeated().encodedSizeWithTag(2, value.inputs)
        if (value.name != "") size += ProtoAdapter.STRING.encodedSizeWithTag(3, value.name)
        if (value.cached != false) size += ProtoAdapter.BOOL.encodedSizeWithTag(4, value.cached)
        if (value.started != null) size += ProtoAdapter.INSTANT.encodedSizeWithTag(5, value.started)
        if (value.completed != null) size += ProtoAdapter.INSTANT.encodedSizeWithTag(6,
            value.completed)
        if (value.error != "") size += ProtoAdapter.STRING.encodedSizeWithTag(7, value.error)
        return size
      }

      public override fun encode(writer: ProtoWriter, value: Vertex): Unit {
        if (value.digest != "") ProtoAdapter.STRING.encodeWithTag(writer, 1, value.digest)
        ProtoAdapter.STRING.asRepeated().encodeWithTag(writer, 2, value.inputs)
        if (value.name != "") ProtoAdapter.STRING.encodeWithTag(writer, 3, value.name)
        if (value.cached != false) ProtoAdapter.BOOL.encodeWithTag(writer, 4, value.cached)
        if (value.started != null) ProtoAdapter.INSTANT.encodeWithTag(writer, 5, value.started)
        if (value.completed != null) ProtoAdapter.INSTANT.encodeWithTag(writer, 6, value.completed)
        if (value.error != "") ProtoAdapter.STRING.encodeWithTag(writer, 7, value.error)
        writer.writeBytes(value.unknownFields)
      }

      public override fun decode(reader: ProtoReader): Vertex {
        var digest: String = ""
        val inputs = mutableListOf<String>()
        var name: String = ""
        var cached: Boolean = false
        var started: Instant? = null
        var completed: Instant? = null
        var error: String = ""
        val unknownFields = reader.forEachTag { tag ->
          when (tag) {
            1 -> digest = ProtoAdapter.STRING.decode(reader)
            2 -> inputs.add(ProtoAdapter.STRING.decode(reader))
            3 -> name = ProtoAdapter.STRING.decode(reader)
            4 -> cached = ProtoAdapter.BOOL.decode(reader)
            5 -> started = ProtoAdapter.INSTANT.decode(reader)
            6 -> completed = ProtoAdapter.INSTANT.decode(reader)
            7 -> error = ProtoAdapter.STRING.decode(reader)
            else -> reader.readUnknownField(tag)
          }
        }
        return Vertex(
          digest = digest,
          inputs = inputs,
          name = name,
          cached = cached,
          started = started,
          completed = completed,
          error = error,
          unknownFields = unknownFields
        )
      }

      public override fun redact(value: Vertex): Vertex = value.copy(
        started = value.started?.let(ProtoAdapter.INSTANT::redact),
        completed = value.completed?.let(ProtoAdapter.INSTANT::redact),
        unknownFields = ByteString.EMPTY
      )
    }

    private const val serialVersionUID: Long = 0L
  }
}