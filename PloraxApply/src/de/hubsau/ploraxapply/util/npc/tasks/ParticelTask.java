package de.hubsau.ploraxapply.util.npc.tasks;
/*Class erstellt von Hubsau


21:03 2019 29.03.2019
Wochentag : Freitag


*/


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.inventivetalent.particle.ParticleEffect;

public class ParticelTask implements Runnable {


    final Location middle;
    int degree = 0;
    private boolean down;

    public ParticelTask(Location middle) {

        degree = 0;
        this.middle = middle;

        down = false;


    }




    @Override
    public void run() {




        if(degree < 360){





            if(!down) {



                degree += 5;




                double radians = Math.toRadians(degree);
                double x = Math.cos(radians);
                double z = Math.sin(radians);


                middle.add(x, 0.04, z);
                ParticleEffect.FLAME.send(Bukkit.getOnlinePlayers(), middle, 0, 0, 0, 0, 6);
                middle.subtract(x, 0, z);




            }else{


                    degree += 5;


                    double radians = Math.toRadians(degree);
                    double x = Math.cos(radians);
                    double z = Math.sin(radians);


                    middle.add(x, 0, z);
                    middle.subtract(0, 0.04, 0);




                ParticleEffect.FLAME.send(Bukkit.getOnlinePlayers(), middle, 0, 0, 0, 0, 6);
                    middle.subtract(x, 0, z);


            }


        }else{
            if(degree >= 360) {

                if(! down) {
                    down = true;


                }else {
                    down = false;


                }

                degree = 0;
            }

        }










    }
}
