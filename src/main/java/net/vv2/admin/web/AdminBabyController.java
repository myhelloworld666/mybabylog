package net.vv2.admin.web;

import com.xiaoleilu.hutool.date.DateUtil;
import net.vv2.baby.domain.Baby;
import net.vv2.baby.domain.User;
import net.vv2.baby.service.UserService;
import net.vv2.baby.service.impl.BabyServiceImpl;
import net.vv2.baby.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author J.Sky bosichong@qq.com
 * @create 2017-06-19 21:41
 **/
@Controller
@RequestMapping("/admin/baby")
public class AdminBabyController {
    @Autowired
    private BabyServiceImpl babyService;
    @Autowired
    private UserServiceImpl UserService;



    /**
     * 宝贝列表页
     * @param model
     * @return
     */
    @RequestMapping("/babyList")
    public String babyList(Model model){
        List<Baby> list = babyService.selectAllBaby();
        Baby b = new Baby();
      // String bap[][] = new String[list.size()][4];
        //List pname = new ArrayList();
        for(int i = 0;i < list.size();i++){//遍历获得父母名字
           // pname.add(UserService.selectUserById(list.get(i).getPartent_id()).getName());
            b=list.get(i);
            b.setPartent_name(UserService.selectUserById(list.get(i).getPartent_id()).getName());

        }
        //model.addAttribute("pname",pname);
        model.addAttribute("list",list);

        return "/admin/baby/babyList";
    }

    /**
     * add baby
     * @return
     */
    @RequestMapping("/addBaby")
    public String addBaby(Model model){
        List<User> plist = UserService.selectAll();
        System.out.println("------------------------------------------"+plist);
        model.addAttribute("plist",plist);
        return "/admin/baby/addBaby";
    }

    /**
     * save babydata
     * @param name
     * @param brithday
     * @param mv
     * @return
     */
    @RequestMapping("/saveBaby")
    public ModelAndView saveBaby(String name,
                                 Integer partent_id,
                                 String brithday,
                                 ModelAndView mv){
        Baby baby = new Baby(name,partent_id,DateUtil.parse(brithday));
        return returnMv((babyService.addBaby(baby)>0),mv);

    }

    /**
     * baby更新页
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/editBaby/{id}")
    public String editBaby(@PathVariable Integer id,
                           Model model){
        Baby baby = babyService.selectBabyById(id);
        List<User> plist = UserService.selectAll();
        System.out.println("------------------------------------------"+plist);
        model.addAttribute("plist",plist);
        model.addAttribute("baby",baby);
        return "/admin/baby/editBaby";

    }


    /**
     * 更新宝贝数据
     *
     * @param id
     * @param name
     * @param brithday
     * @param mv
     * @return
     */
    @RequestMapping("/updBaby")
    public ModelAndView updBaby(Integer id,
                                String name,
                                Integer partent_id,
                                String brithday,
                                ModelAndView mv){
//        Baby baby = new Baby();
//
//        baby.setName(name);
//        baby.setBrithday(DateUtil.parse(brithday));
        Baby baby = new Baby(name,partent_id,DateUtil.parse(brithday));
        baby.setId(id);
        return returnMv((babyService.updBaby(baby)>0),mv);


    }

    /**
     * 删除宝贝
     * @param id
     * @param mv
     * @return
     */
    @RequestMapping("/delBaby/{id}")
    public ModelAndView delBaby(@PathVariable Integer id,
                                ModelAndView mv){

        return returnMv((babyService.delBaby(id)>0),mv);

    }






    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 选择页面跳转
     *
     * @param bl
     * @param mv
     * @return
     */
    public ModelAndView returnMv(boolean bl, ModelAndView mv) {
        if (bl) {
            return updateDate(mv, "操作成功！", "<meta http-equiv=\"refresh\" content=\"2;url=/admin/baby/babyList\">", "/success");
        } else {
            return updateDate(mv, "操作失败！", "<meta http-equiv=\"refresh\" content=\"2;url=/admin/baby/babyList\">", "/err");
        }

    }

    /**
     * 页面跳转
     *
     * @param mv
     * @param msg
     * @param url
     * @param viewName
     * @return
     */
    public ModelAndView updateDate(ModelAndView mv, String msg, String url, String viewName) {
        mv.addObject("msg", msg);
        mv.addObject("url", url);
        mv.setViewName(viewName);
        return mv;
    }
}
