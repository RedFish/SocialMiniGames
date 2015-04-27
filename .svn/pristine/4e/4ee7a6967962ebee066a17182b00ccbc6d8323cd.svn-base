package controlers;

import graphics.VueJeuDeLettres;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CheatListenerJeuDeLettres implements KeyListener
{
	VueJeuDeLettres vue;
	
	public CheatListenerJeuDeLettres(VueJeuDeLettres vue)
	{
		this.vue = vue;
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if ((e.getModifiers()==KeyEvent.CTRL_MASK) && (e.getKeyCode()==KeyEvent.VK_C))
            vue.cheatAfficherMots();
	}

	@Override
	public void keyReleased(KeyEvent arg0)
	{
	}

	@Override
	public void keyTyped(KeyEvent arg0)
	{
	}
}