/*
    关于流:
        -流根据方向可以分为「输入流」和「输出流」, 输入和输出是相对于内存而言的, 从内存出来即输出, 到内存中即输入
        -输入流又叫InputStream, 输出流又叫OutputStream. 输入也可称为「读(Read)」, 「写(Write)」.

        -流根据读取数据的方式可以分为「字节流」和「字符流」. 字节流是按照字节方式读取, 字符流按照字符方式读取(一次读取2字节, Java语言中一个字符占2字节)
        -字节流适合读取: 视频, 声音, 图片等二进制文件.
        -字符流适合读取: 纯文本文件.

        -Java中所有的字节流都以Stream结尾, 所有的字符流都含有Reader或Writer.

        关于四个主要的流:
            -InputStream
                -字节输入流
                -实现Closeable接口

                -主要子类:
                    -FileInputStream
                        -java.io.FileInputStream; 文件字节输入流
                        -按照字节方式读取文件
                    -FilterInputStream
                        -使用较少
                        -主要子类
                            -BufferedInputStream
                            -DataInputStream
                    -ObjectInputStream

            -OutputStream
                -字节输出流
                -实现Closeable接口和Flushable接口

                -主要子类:
                    -FileOutputStream
                    -FilterOutputStream
                        -使用较少

                        -主要子类
                            -BufferedOutputStream
                            -DataOutputStream
                            -PrintStream
                    -ObjectOutputStream
            -Reader
                -字符输入流
                -实现Closeable接口

                -主要子类:
                    -InputStreamReader
                        -FileReader
                    -BufferedReader
            -Writer
                -字符输出流
                -实现Closeable接口和Flushable接口

                -主要子类:
                    -OutputStreamWriter
                        -FileWriter
                    -BufferedWriter
                    -PrintWriter
*/