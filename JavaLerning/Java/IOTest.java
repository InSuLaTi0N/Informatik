/*
    ������:
        -�����ݷ�����Է�Ϊ�����������͡��������, ����������������ڴ���Ե�, ���ڴ���������, ���ڴ��м�����
        -�������ֽ�InputStream, ������ֽ�OutputStream. ����Ҳ�ɳ�Ϊ����(Read)��, ��д(Write)��.

        -�����ݶ�ȡ���ݵķ�ʽ���Է�Ϊ���ֽ������͡��ַ�����. �ֽ����ǰ����ֽڷ�ʽ��ȡ, �ַ��������ַ���ʽ��ȡ(һ�ζ�ȡ2�ֽ�, Java������һ���ַ�ռ2�ֽ�)
        -�ֽ����ʺ϶�ȡ: ��Ƶ, ����, ͼƬ�ȶ������ļ�.
        -�ַ����ʺ϶�ȡ: ���ı��ļ�.

        -Java�����е��ֽ�������Stream��β, ���е��ַ���������Reader��Writer.

        �����ĸ���Ҫ����:
            -InputStream
                -�ֽ�������
                -ʵ��Closeable�ӿ�

                -��Ҫ����:
                    -FileInputStream
                        -java.io.FileInputStream; �ļ��ֽ�������
                        -�����ֽڷ�ʽ��ȡ�ļ�
                    -FilterInputStream
                        -ʹ�ý���
                        -��Ҫ����
                            -BufferedInputStream
                            -DataInputStream
                    -ObjectInputStream

            -OutputStream
                -�ֽ������
                -ʵ��Closeable�ӿں�Flushable�ӿ�

                -��Ҫ����:
                    -FileOutputStream
                    -FilterOutputStream
                        -ʹ�ý���

                        -��Ҫ����
                            -BufferedOutputStream
                            -DataOutputStream
                            -PrintStream
                    -ObjectOutputStream
            -Reader
                -�ַ�������
                -ʵ��Closeable�ӿ�

                -��Ҫ����:
                    -InputStreamReader
                        -FileReader
                    -BufferedReader
            -Writer
                -�ַ������
                -ʵ��Closeable�ӿں�Flushable�ӿ�

                -��Ҫ����:
                    -OutputStreamWriter
                        -FileWriter
                    -BufferedWriter
                    -PrintWriter
*/