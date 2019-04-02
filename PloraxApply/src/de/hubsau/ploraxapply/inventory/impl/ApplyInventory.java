package de.hubsau.ploraxapply.inventory.impl;
/*Class erstellt von Hubsau


23:32 2019 29.03.2019
Wochentag : Freitag


*/


import de.hubsau.ploraxapply.inventory.AbstractInventory;
import de.hubsau.ploraxapply.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class ApplyInventory extends AbstractInventory {

    private ItemStack book1;
    private ItemStack book2;
    private ItemStack book3;

    public ApplyInventory() {
        super(Bukkit.createInventory(null, 9*3, "§7➜ §e§lHubsau §6Bewerbung"));


    }
    public void fill(){

        setItem(4, new ItemBuilder(Material.DIAMOND).name("§7§l➜ §e§lDeveloper §6Bewerbung").build());
        book1 = new ItemBuilder(Material.BOOK).name("§7§l➜ §e§lWer bin ich?").build();
        book2 = new ItemBuilder(Material.BOOK).name("§7§l➜ §e§lWarum ich?").build();
        book3 = new ItemBuilder(Material.BOOK).name("§7§l➜ §e§lMein Erfahrungen").build();

        setItem(10, book1);
        setItem(16, book2);
        setItem(22, book3);



    }


    public ItemStack getBook1() {
        return book1;
    }

    public ItemStack getBook2() {
        return book2;
    }

    public ItemStack getBook3() {
        return book3;
    }
}
