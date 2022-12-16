package io.klibs.serial

/**
 * Returns a [ByteArray] containing the 2 bytes that make up the target `UShort`
 * value.
 *
 * @param be Whether the bytes should be in Big Endian order.
 *
 * Default: `true`
 *
 * @return An array of 2 bytes.
 */
fun UShort.getBytes(be: Boolean = true): ByteArray {
  val out = ByteArray(2)
  unsafeGetBytes(out, 0, be)
  return out
}

/**
 * Fills the given [buffer], starting from the given [offset], with the 2 bytes
 * that make up the target `UShort` value.
 *
 * If the given array's size is less than `offset + 2`, an exception will be
 * thrown.
 *
 * Order is Big Endian.
 *
 * @param buffer `ByteArray` that will be filled with the bytes from the target
 * `UShort`.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @param be Whether the bytes should be written in Big Endian order.
 *
 * Default: `true`
 *
 * @return `2`, the number of bytes written to the buffer.
 */
fun UShort.getBytes(buffer: ByteArray, offset: Int = 0, be: Boolean = true): Int {
  if (offset + 2 >= buffer.size)
    throw Throwable("Writing the value $this to the given buffer would cause it to overflow!")
  unsafeGetBytes(buffer, offset, be)
  return 2
}

/**
 * Returns a [UByteArray] containing the 2 unsigned bytes that make up the
 * target `UShort` value.
 *
 * @param be Whether the bytes should be in Big Endian order.
 *
 * Default: `true`
 *
 * @return An array of 2 unsigned bytes.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun UShort.getUBytes(be: Boolean = true): UByteArray {
  val out = UByteArray(2)
  unsafeGetBytes(out.asByteArray(), 0, be)
  return out
}

/**
 * Fills the given [buffer], starting from the given [offset], with the 2 bytes
 * that make up the target `UShort` value.
 *
 * If the given array's size is less than `offset + 2`, an exception will be
 * thrown.
 *
 * @param buffer `UByteArray` that will be filled with the bytes from the target
 * `UShort`.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @param be Whether the bytes should be written in Big Endian order.
 *
 * Default: `true`
 *
 * @return `2`, the number of bytes written to the buffer.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun UShort.getUBytes(buffer: UByteArray, offset: Int = 0, be: Boolean = true) = getBytes(buffer.asByteArray(), offset, be)

private inline fun UShort.unsafeGetBytes(buffer: ByteArray, offset: Int, be: Boolean) {
  val v = this.toInt()
  if (be) {
    buffer[offset    ] = ((v shr 8) and 0xFF).toByte()
    buffer[offset + 1] = ((v      ) and 0xFF).toByte()
  } else {
    buffer[offset + 1] = ((v shr 8) and 0xFF).toByte()
    buffer[offset    ] = ((v      ) and 0xFF).toByte()
  }
}