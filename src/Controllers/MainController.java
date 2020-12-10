package Controllers;

import javax.swing.SwingUtilities;

import Models.Worlds.World;
import Views.MainFrame;

public class MainController {

    private static World world;
    private static MainFrame frame;
    public static void main(String[] args) throws Exception {
        
        SwingUtilities.invokeLater(new Runnable(){
        
            @Override
            public void run() {
                frame = new MainFrame();
                world = null;
            }
        });
    }

    public static World getWorld() {
        return world;
    }

    public static void addWorld(World obj) {
        world = obj;
    }

    public static MainFrame getMainFrame() {
        return frame;
    }
}
