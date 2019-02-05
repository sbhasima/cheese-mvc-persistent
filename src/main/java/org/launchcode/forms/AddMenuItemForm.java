package org.launchcode.forms;

import org.launchcode.models.Cheese;
import org.launchcode.models.Menu;

import javax.validation.constraints.NotNull;

public class AddMenuItemForm {

    //fields to process the form
    @NotNull
    private int menuId;

    @NotNull
    private int cheeseId;

    //fields to render the form
    private Menu menu;
    private Iterable<Cheese> cheeses;

    //constructors
    public AddMenuItemForm() { }

    public AddMenuItemForm(Iterable<Cheese> cheeses, Menu menu){
        this.cheeses = cheeses;
        this.menu = menu;
    }

    public int getMenuId() { return menuId;   }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getCheeseId() { return cheeseId;   }

    public void setCheeseId(int cheeseId) {
        this.cheeseId = cheeseId;
    }

    public Menu getMenu() { return menu;  }

    public Iterable<Cheese> getCheeses() {
        return cheeses;
    }
}
