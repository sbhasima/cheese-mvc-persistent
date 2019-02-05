package org.launchcode.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.launchcode.forms.AddMenuItemForm;
import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;
import org.launchcode.models.data.CheeseDao;
import org.launchcode.models.data.MenuDeo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value= "menu")
public class MenuController {
    @Autowired
    MenuDeo menuDeo;

    @Autowired
    CheeseDao cheeseDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "menus");
        model.addAttribute("menus", menuDeo.findAll());
        return "menu/index";
    }
    //display add form
    @RequestMapping(value="add", method= RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("title", "Add Menu");
        model.addAttribute(new Menu());

        return "menu/add";
    }

    // process add form
    @RequestMapping(value= "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid Menu menu, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Menu");
            return "menu/add";
        }
        menuDeo.save(menu);
        return "redirect:view?menuId=" + menu.getId();
    }

    // view particular item with given Id
    @RequestMapping(value="view", method= RequestMethod.GET)
    public String viewMenu(Model model, @RequestParam int menuId){
        Menu menu = menuDeo.findOne(menuId);
        model.addAttribute("title", menu.getName());
        model.addAttribute("cheeses", menu.getCheeses());
        model.addAttribute("menu", menu);
        return "menu/view";
    }

    //display "add menu item" form
    @RequestMapping(value = "add-item", method = RequestMethod.GET)
    public String addItem(Model model, @RequestParam int menuId){
        Menu menu = menuDeo.findOne(menuId);
        AddMenuItemForm form = new AddMenuItemForm(cheeseDao.findAll(), menu);

        model.addAttribute("title", "Add Item to menu:"+ menu.getName());
        model.addAttribute("form", form);

        return "menu/add-item";

    }

    //processing form
    @RequestMapping(value= "add-item", method = RequestMethod.POST)
    public String addItem(Model model, @Valid AddMenuItemForm form, Errors errors){

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "menu/add-item";
        }

        Cheese theCheese = cheeseDao.findOne(form.getCheeseId());
        Menu theMenu = menuDeo.findOne(form.getMenuId());
        theMenu.addItem(theCheese);
        menuDeo.save(theMenu);
        return "redirect:/menu/view?menuId=" + theMenu.getId();

    }

    }


