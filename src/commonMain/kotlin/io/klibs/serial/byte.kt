package io.klibs.serial

/**
 * Returns a [ByteArray] containing the target value.
 *
 * @return An array containing the target byte.
 */
fun Byte.getBytes() = byteArrayOf(this)

/**
 * Fills the given [buffer], starting from the given [offset], with the target
 * value.
 *
 * If the given array's size is less than `offset + 1`, an exception will be
 * thrown.
 *
 * @param buffer `ByteArray` that will be filled with the target byte.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @return `1`, the number of bytes written to the buffer.
 */
fun Byte.getBytes(buffer: ByteArray, offset: Int = 0): Int {
  if (offset + 1 >= buffer.size)
    throw Throwable("Writing the value $this to the given buffer would cause it to overflow!")
  buffer[offset] = this
  return 1
}

/**
 * Returns a [UByteArray] containing the target value.
 *
 * @return An array of containing the target byte.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun Byte.getUBytes() = ubyteArrayOf(this.toUByte())

/**
 * Fills the given [buffer], starting from the given [offset], with the target
 * value.
 *
 * If the given array's size is less than `offset + 1`, an exception will be
 * thrown.
 *
 * @param buffer `UByteArray` that will be filled with the target byte.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @return `1`, the number of bytes written to the buffer.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun Byte.getUBytes(buffer: UByteArray, offset: Int = 0) = getBytes(buffer.asByteArray(), offset)
