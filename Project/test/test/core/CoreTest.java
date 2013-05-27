package test.core;

import base.Game;
import util.Vec2D;

import org.junit.Test;
import org.junit.Before;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests basic core engine functionality
 */
public class CoreTest {
	private Game game;
	private Ent_Test ent;
	private boolean success=false;
	@Before
	public void setUp() {
		game=new Game();
		ent = new Ent_Test(new Vec2D(0,-50),new Vec2D(0,-15));
		Game.STATEMAN.add_ent(ent);
	}

	@Test
	public void fall() throws Exception {
		ActionListener checker = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				success=Double.compare(11,ent.pos.length())>0;
				Game.quit();
			}
		};
		new Timer(7000,checker).start();
		game.run();
		assertTrue("Pos.length() was "+ent.pos.length(),success);
	}

	@Test
	public void stack() throws Exception {
		Ent_Test ent2 = new Ent_Test(new Vec2D(0,-10),new Vec2D());
		Game.STATEMAN.add_ent(ent2);
		ActionListener checker = new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				success=Double.compare(31,ent.pos.length())>0;
				success=success&&Double.compare(29,ent.pos.length())<0;
				Game.quit();
			}
		};
		new Timer(7000,checker).start();
		game.run();
		assertTrue("Pos.length() was "+ent.pos.length(),success);
	}


}
