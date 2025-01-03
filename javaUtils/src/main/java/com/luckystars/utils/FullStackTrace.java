package com.luckystars.utils;

import org.apache.commons.io.output.StringBuilderWriter;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class FullStackTrace {

    public String getStackTrace(Throwable e){
        StringBuilderWriter sw = new StringBuilderWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public String getStackTrace2(Throwable e){
        JDKStringBuilderWriter sw2 = new JDKStringBuilderWriter();
        e.printStackTrace(new PrintWriter(sw2));
        return sw2.toString();
    }

    @Test
    public void test(){
        System.out.println(getStackTrace(new RuntimeException()));
    }

    //没有依赖 commons-io时可以用这个
    public static class JDKStringBuilderWriter extends Writer{

        private StringBuilder buf;
        public JDKStringBuilderWriter() {
            buf = new StringBuilder();
            lock = buf;
        }

        /**
         * Create a new string writer using the specified initial string-buffer
         * size.
         *
         * @param initialSize
         *        The number of <tt>char</tt> values that will fit into this buffer
         *        before it is automatically expanded
         *
         * @throws IllegalArgumentException
         *         If <tt>initialSize</tt> is negative
         */
        public JDKStringBuilderWriter(int initialSize) {
            if (initialSize < 0) {
                throw new IllegalArgumentException("Negative buffer size");
            }
            buf = new StringBuilder(initialSize);
            lock = buf;
        }

        public void write(int c) {
            buf.append((char) c);
        }

        /**
         * Write a portion of an array of characters.
         *
         * @param  cbuf  Array of characters
         * @param  off   Offset from which to start writing characters
         * @param  len   Number of characters to write
         */
        public void write(char cbuf[], int off, int len) {
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                    ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return;
            }
            buf.append(cbuf, off, len);
        }

        public void write(String str) {
            buf.append(str);
        }

        /**
         * Write a portion of a string.
         *
         * @param  str  String to be written
         * @param  off  Offset from which to start writing characters
         * @param  len  Number of characters to write
         */
        public void write(String str, int off, int len)  {
            buf.append(str.substring(off, off + len));
        }

        /**
         * Appends the specified character sequence to this writer.
         *
         * <p> An invocation of this method of the form <tt>out.append(csq)</tt>
         * behaves in exactly the same way as the invocation
         *
         * <pre>
         *     out.write(csq.toString()) </pre>
         *
         * <p> Depending on the specification of <tt>toString</tt> for the
         * character sequence <tt>csq</tt>, the entire sequence may not be
         * appended. For instance, invoking the <tt>toString</tt> method of a
         * character buffer will return a subsequence whose content depends upon
         * the buffer's position and limit.
         *
         * @param  csq
         *         The character sequence to append.  If <tt>csq</tt> is
         *         <tt>null</tt>, then the four characters <tt>"null"</tt> are
         *         appended to this writer.
         *
         * @return  This writer
         *
         * @since  1.5
         */
        public JDKStringBuilderWriter append(CharSequence csq) {
            if (csq == null)
                write("null");
            else
                write(csq.toString());
            return this;
        }

        /**
         * Appends a subsequence of the specified character sequence to this writer.
         *
         * <p> An invocation of this method of the form <tt>out.append(csq, start,
         * end)</tt> when <tt>csq</tt> is not <tt>null</tt>, behaves in
         * exactly the same way as the invocation
         *
         * <pre>
         *     out.write(csq.subSequence(start, end).toString()) </pre>
         *
         * @param  csq
         *         The character sequence from which a subsequence will be
         *         appended.  If <tt>csq</tt> is <tt>null</tt>, then characters
         *         will be appended as if <tt>csq</tt> contained the four
         *         characters <tt>"null"</tt>.
         *
         * @param  start
         *         The index of the first character in the subsequence
         *
         * @param  end
         *         The index of the character following the last character in the
         *         subsequence
         *
         * @return  This writer
         *
         * @throws  IndexOutOfBoundsException
         *          If <tt>start</tt> or <tt>end</tt> are negative, <tt>start</tt>
         *          is greater than <tt>end</tt>, or <tt>end</tt> is greater than
         *          <tt>csq.length()</tt>
         *
         * @since  1.5
         */
        public JDKStringBuilderWriter append(CharSequence csq, int start, int end) {
            CharSequence cs = (csq == null ? "null" : csq);
            write(cs.subSequence(start, end).toString());
            return this;
        }

        /**
         * Appends the specified character to this writer.
         *
         * <p> An invocation of this method of the form <tt>out.append(c)</tt>
         * behaves in exactly the same way as the invocation
         *
         * <pre>
         *     out.write(c) </pre>
         *
         * @param  c
         *         The 16-bit character to append
         *
         * @return  This writer
         *
         * @since 1.5
         */
        public JDKStringBuilderWriter append(char c) {
            write(c);
            return this;
        }

        public String toString() {
            return buf.toString();
        }

        public StringBuilder getBuffer() {
            return buf;
        }

        public void flush() {
        }

        public void close() throws IOException {
        }

    }

}
