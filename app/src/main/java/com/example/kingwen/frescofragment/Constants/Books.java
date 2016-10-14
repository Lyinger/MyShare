package com.example.kingwen.frescofragment.Constants;

import com.example.kingwen.frescofragment.Beans.Bill;
import com.example.kingwen.frescofragment.Beans.Booker;
import com.example.kingwen.frescofragment.Beans.PublishBook;

/**
 * Created by kingwen on 2016/6/2.
 */
public class Books {

    //书籍的发布方式参见常量1送 2 换  3卖  4借
    public  static  final  int  BOOK_DELIVER=1;
    public  static  final  int  BOOK_EXCHANGE=2;
    public  static  final  int  BOOK_SALL=3;
    public  static  final  int  BOOK_BRORROW=4;


    /**
     * 我的借阅界面查看所有的时候需要传递的参数
     */
    public static  final String MYBORROW_LENDING="当前借阅";

    public static  final String MYBORROW_LENT="借阅历史";


    // 1小说
    public static PublishBook book_story_heyisheng=new PublishBook(Nets.BOOK_URL_HEYI,"何以笙箫默","顾漫","催人泪下的校园跨越多年的爱情故事",1,3,"小说，我不愿意将就",Nets.PHOTO_URL_HEYI,"android小白");
    public static PublishBook book_story_wanmei=new PublishBook(Nets.BOOK_URL_WANMEI,"完美世界","辰东","一粒尘可填海，一根草斩尽日月星辰，弹指间天翻地覆。 " +
            "群雄并起，万族林立，诸圣争霸，乱天动地。问苍茫大地，谁主沉浮？！ 一个少年从大荒中走出，一切从这里开始…",1,4,"这应该是最好看的玄幻小说了，场面恢弘，情节曲折，非常值得一看",Nets.PHOTO_URL_WANMEI,"石昊");

    //2传记
    public static PublishBook book_bio_jobs =new PublishBook(Nets.BOOK_URL_JOBS,"史蒂夫·乔布斯传","沃尔特·艾萨克森","真正的乔帮主的一生，读这本书之前，我觉得他是天才，读完之后，他更是天才",2,3,"除了震撼还是震撼",Nets.PHOTO_URL_JOBS,"w");
    public static PublishBook book_bio_fangao=new PublishBook(Nets.BOOK_URL_FANGAO,"渴望生活:梵高传","欧文斯通","斯通的笔写出了梵高的魂——一个因善良受苦的天使　一个用色彩享乐的天才",2,2,"心疼这位老人",Nets.PHOTO_URL_FANGAO,"向日葵");
    public static PublishBook book_bio_deng=new PublishBook(Nets.BOOK_URL_DENGXIAOPING,"邓小平传","傅高义","今天我们仍然生活在——《邓小平时代》",2,2,"这是最好的时代，一个由一位老人开创的时代",Nets.PHOTO_URL_DENGXIAOPING," 沐薰兒");

    //3生活
    public static PublishBook book_life_bodylove=new PublishBook(Nets.BOOK_URL_BODYLOVE,"跟身体谈恋爱","伊能静","唯有身、心、灵内外兼修的正能量方可缔造永恒之美",4,2,"这个世界可能有人不喜欢钱，但是没有人不喜欢美",Nets.PHOTO_URL_BODYLOVE,"浩然正气");

    //4专业
    public static PublishBook book_android=new PublishBook(Nets.BOOK_URL_Android,"第一行代码","郭霖","android入门的经典之作",4,2,"",Nets.PHOTO_URL_Android,"android小白");
    public static PublishBook book_java=new PublishBook(Nets.BOOK_URL_JAVA,"think in java","Bruce Eckel","java大神之路的必备经典",4,1,"重温，希望找个一起的小伙伴",Nets.PHOTO_URL_JAVA,"墨白");

   // 5期刊杂志
    public static PublishBook book_journal_lonely=new PublishBook(Nets.BOOK_URL_LONELYPLANET,"Lonely Planet孤独星球:日本(2016年版)","孤独星球","一本有态度、负责任的旅行指南，" +
           "用我们独特的旅行梦想，与你相伴相随，让你的行程变得简单、丰富而美好。",5,2,"行千里路，读万卷书",Nets.PHOTO_URL_LONELYPLANET,"阿尤");
    public static PublishBook book_journal_reader=new PublishBook(Nets.BOOK_URL_READER,"读者合订本(2016年春季卷)(总606-611期)","李秀娟","适合读者阅读的《读者》",5,3,"希望和大家分享这本书",Nets.PHOTO_URL_READER,"书尤药也");
    public static PublishBook book_journal_guojiadili=new PublishBook(Nets.BOOK_URL_GUOJIADILI,"中国国家地理(2016年7月刊) ","李栓科","中国大陆著名的有关地理的杂志",5,4,"从初中一直到大学不间断的一本书",Nets.PHOTO_URL_GUOJIADILI,"行万里路");

    //6其他
    public static PublishBook book_other_lonelyandhonor=new PublishBook(Nets.BOOK_URL_LONELYANDHONOR,"你的孤独，虽败犹荣","刘同","孤独之前是迷茫，孤独之后是成长",4,1,"刘同老师的书我都看过，我是他的忠实粉丝，有同样喜欢他的小伙伴吗 ",Nets.PHOTO_URL_LONELYANDHONOR,"马勋");
    public static PublishBook book_other_buddha=new PublishBook(Nets.BOOK_URL_BUDDHA,"阿弥陀佛么么哒","大冰","一本有温度有激情的江湖小说",6,1,"很喜欢大冰老师的书，希望分享给同样喜欢他的小伙伴",Nets.PHOTO_URL_BUDDHA,"茄子");


    public static PublishBook book=new PublishBook(Nets.BOOK_URL_BUDDHA,"阿弥陀佛么么哒","大冰","一本有温度有激情的江湖小说",6,1,"很喜欢大冰老师的书，希望分享给同样喜欢他的小伙伴",Nets.PHOTO_URL_BUDDHA,"茄子");





    public  static Booker booker=new Booker(Nets.PHOTO_URL1,"茄子","1262126358@qq.com",1,"山东大学","爱看书爱萌娃，欢迎交流");
    public static Bill billfirst=new Bill(0,10,"红楼梦","玄机和尚","2016,6,12");
    public static Bill billsecond =new Bill(1,10,"数据结构","梦幻神机","2016,5,12");




}
