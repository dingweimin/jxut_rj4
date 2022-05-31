import com.jxut.dao.UserDao;
import com.jxut.dao.impl.UserDaoImpl;
import com.jxut.model.User;
import org.junit.Test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: WeiMin
 * @Date: 2022/5/26 17:35
 */

public class Test1 {
            @Test
            //随机数据测试
     public void test1() throws ParseException {
                RollValue RollValue = new RollValue();
                String cname = RollValue.randomName(true, 3);
                String mobile = RollValue.createMobile(1);
                String wname = RollValue.getName();
                String road = RollValue.getRoad();
                String sex =RollValue.getRandomSex();
                System.out.println(sex);
                System.out.println(road);
                System.out.println(wname);
                System.out.println(mobile);
                System.out.println(cname);
                String l = RollValue.rollTime();
                //格式化输出日期
                System.out.printf("从1999-11-30 00:00:00到现在的一个随机日期为:%1$tF %1$tT", l);
                System.out.println(l);

            }
            @Test
    public  void  testDao() throws ParseException {
                UserDao userDao = new UserDaoImpl();
                RollValue RollValue = new RollValue();
                String cname = RollValue.randomName(true, 3);
                String mobile = RollValue.createMobile(1);
                String wname = RollValue.getName();
                String road = RollValue.getRoad();

                User user = new User();
                HashSet set = new HashSet();


                for (int j = 0; j < 100; j++) {

                    String chineseName = RollValue.randomName(true, 3);
                    if (!set.contains(chineseName)) {

                        set.add(chineseName);

                    }
                }
                Iterator iterator = set.iterator();
                    while (iterator.hasNext()) {
                        for (int i = 0; i < 100; i++) {
                            String time = RollValue.rollTime();
                            Date data = java.sql.Date.valueOf(time);
                            user.setUserName(RollValue.getName());
                            user.setRealName(iterator.next().toString());
                            System.out.println(RollValue.getName());
                            user.setPassword("123");
                            user.setSex(RollValue.getRandomSex());
                          user.setBirthday(data);
                            user.setTel(RollValue.createMobile(1));
                            user.setAddress(RollValue.getRoad());
                            user.setType("1");
                            userDao.addUser(user);
                        }
                    }
                }


//    public static void main(String[] args) {
//
///**随机产生100个昵称*/
//
//        HashSet set = new HashSet();
//
//        for (int i = 0; i < 100; i++) {
//
//            String chineseName = randomName(true,3);
//
//            if (!set.contains(chineseName)) {
//
//                set.add(chineseName);
//
//            }
//
//        }

//        System.out.println(set);
//        Iterator iterator = set.iterator();
//        while (iterator.hasNext()) {
//            System.out.println(iterator.next().toString());
//        }

//        UserDao userDao = new UserDaoImpl();
//        User user = new User(); while (iterator.hasNext()) {
//        for (int i = 0; i < 100; i++) {
//           user.setUserName("user"+i);
//
//                user.setRealName(iterator.next().toString());
//                System.out.println(user.getRealName());
//
//            user.setPassword("123");
//            user.setSex("1");
//           user.setType("1");
//           userDao.addUser(user);
//        } }
//    }


                /**方法2*/

}
