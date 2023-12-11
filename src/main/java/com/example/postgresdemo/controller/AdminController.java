package com.example.postgresdemo.controller;

import java.util.List;
import java.util.Locale.Category;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.postgresdemo.dao.BoDeDAO;
import com.example.postgresdemo.dao.MonHocDAO;
import com.example.postgresdemo.dao.NguoiDungDAO;
import com.example.postgresdemo.dao.TongKetDAO;
import com.example.postgresdemo.model.BoDe;
import com.example.postgresdemo.model.MonHoc;
import com.example.postgresdemo.model.NguoiDung;
import com.example.postgresdemo.model.Tongket;

@Controller
public class AdminController {
    @Autowired
    NguoiDungDAO ndd;
    @Autowired
    MonHocDAO mhdao;

    @Autowired
    BoDeDAO bddao;

    @Autowired
    TongKetDAO tkdao;

    @RequestMapping("/qluser")
    public String product(Model model) {
        NguoiDung item = new NguoiDung();// item buộc lên form
        model.addAttribute("item", item);

        List<NguoiDung> items = ndd.findAll();// items buộc lên bảng
        model.addAttribute("items", items);
        return "qlUser";
    }

    @RequestMapping("/qluser/createND")
    public String create(NguoiDung item, Model model) {
        if (item.getIdND() == null || item.getIdND().isEmpty()) {
            // Gán giá trị cho idND nếu nó chưa có
            item.setIdND("ND"); // Thay yourLogicToGenerateId() bằng logic của bạn để tạo ID
        }
        ndd.save(item);
        return "redirect:/qluser";

    }

    @RequestMapping("/qluser/delete/{idND}")
    public String delete(@PathVariable("idND") String idND) {
        ndd.deleteById(idND);
        return "redirect:/qluser";
    }

    @RequestMapping("/qluser/edit/{idND}")
    public String edit(Model model, @PathVariable("idND") String idND) {
        NguoiDung item = ndd.findById(idND).get();
        model.addAttribute("item", item);
        List<NguoiDung> items = ndd.findAll();
        model.addAttribute("items", items);
        return "qluser";
    }

    @RequestMapping("/qluser/update")
    public String viewqldiem(NguoiDung item) {
        ndd.save(item);
        return "redirect:/qluser";
    }

    @RequestMapping("/qldiemad")
    public String viewqldiem(Model model) {
        List<MonHoc> subjectList = mhdao.findAll();
        model.addAttribute("subjectList", subjectList);
        model.addAttribute("selectedSubject", "Hóa");
        List<BoDe> deList = bddao.findAll();
        model.addAttribute("deList", deList);
        model.addAttribute("selectedDe", "");
        List<Tongket> tk = tkdao.findAll();
        model.addAttribute("tk", tk);
        return "qlDiemAdmin";

    }

    @RequestMapping("/qlmon")
    public String view(Model model) {
        return "qlMonThi";
    }

    @RequestMapping("/qldethi")
    public String viewqlde(Model model) {

        return "qldethi";
    }

    @RequestMapping("/qlthongke")
    public String viewqlthongke(Model model) {
        return "qlthongke";
    }

    @RequestMapping("/qlban")
    public String viewqlban(Model model) {
        return "qlban";
    }
}
