/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.qq.client.tool;

import java.io.FileNotFoundException;
import java.io.IOException;
import sun.audio.*;

/**
 *
 * 
 */
public class AudioPlay
{


	private static AudioStream play_music;

	public  void soundPlay(String sound)
	{
		try
		{

			play_music = new AudioStream(this.getClass().getResourceAsStream(sound));

			sun.audio.AudioPlayer.player.start(play_music);

		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e){}
	}
}
