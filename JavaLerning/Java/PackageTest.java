/*
    关于package:
        -为了解决类的命名冲突问题, 在类名前加命名空间(包机制)
        
        -Java中使用package定义包(单包, 复包)
        
        -package语句只能出现在.java源文件的第一行
        
        -package定义格式,通常采用公司域名倒叙方式
        -例: com.bjpowernode.oa.system
        -bjpowernode公司开发oa项目, system是oa项目中其中一个模块
        -package定义全格式:
            公司域名/学校域名倒叙.项目名.模块名
            de.tudresden.oose.xyz
            
        -完整的类名是带有包名的
        
        -带有package语句的java源文件必须如下编译:
            javac -d 生成路径 java源文件路径 //-d 为 -directory
        -如下运行:
            java 包名.类名
*/

package javalerning.packagetest;

public class PackageTest
{
    public static void main(String[] args)
    {
        System.out.println("execute");
    }
}