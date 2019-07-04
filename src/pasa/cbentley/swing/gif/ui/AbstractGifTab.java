package pasa.cbentley.swing.gif.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.gif.ctx.SwingGifCtx;
import pasa.cbentley.swing.imytab.AbstractMyTab;
import pasa.cbentley.swing.widgets.b.BLabel;

/**
 * A message with a GIF playing with how to use the daemon
 * @author Charles Bentley
 *
 */
public class AbstractGifTab extends AbstractMyTab {

   /**
    * 
    */
   private static final long   serialVersionUID = 8194278974893842214L;

   private BLabel              labTitle;

   private GifBasicPlayerPanel player;

   private String              gifPath;

   private String              titleKey;

   protected SwingGifCtx            gifc;

   private boolean             isStartAndStop;

   public AbstractGifTab(SwingGifCtx gifc, String id, String gifPath, String titleKey) {
      super(gifc.getSwingCtx(), id);
      this.gifc = gifc;
      this.gifPath = gifPath;
      this.titleKey = titleKey;
      isStartAndStop = true;

   }

   public void tabLostFocus() {
      if (player != null) {
         player.getController().cmdStop();
      }
   }

   public void setStartAndStop(boolean isStartAndStop) {
      this.isStartAndStop = isStartAndStop;
   }

   public void tabGainFocus() {
      if (player != null) {
         if (isStartAndStop) {
            //restart the GIF where it was ?
            player.getController().cmdPlay();
            //set button states?
         }
      }
   }

   public void disposeTab() {
      //#debug
      toDLog().pFlow("", this, AbstractGifTab.class, "disposeTab", ITechLvl.LVL_05_FINE, true);

      if (player != null) {
         if (isStartAndStop) {
            player.getController().cmdStop();
         }
      }
   }

   public void initTab() {
      this.setLayout(new BorderLayout());

      JPanel north = new JPanel();

      labTitle = new BLabel(sc, titleKey);
      north.add(labTitle);

      //in center a GIF demo of the pascal root daemon
      player = new GifBasicPlayerPanel(gifc);
      player.setGifPath(gifPath);
      player.playDefault();
      player.getController().cmdPlay();

      this.add(north, BorderLayout.NORTH);
      this.add(player, BorderLayout.CENTER);
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "AbstractGifTab");
      super.toString(dc.sup());
      //print 
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "AbstractGifTab");
      super.toString(dc.sup1Line());
   }
   //#enddebug

}