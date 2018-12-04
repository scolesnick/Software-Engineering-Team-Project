package messageData;

import java.io.Serializable;
import java.util.ArrayList;

import gameMechanics.Bullet;

public class BulletData implements Serializable{

	
	private int[][] hostBulletCords;
	private int[][] guestBulletCords;
	
	
	public BulletData(ArrayList<Bullet> hostBullets, ArrayList<Bullet> guestBullets) 
	{
		hostBulletCords = new int[hostBullets.size()][2];
		guestBulletCords = new int[guestBullets.size()][2];
		
		for(int i = 0; i < hostBulletCords.length; i++) 
		{
			hostBulletCords[i][0] = hostBullets.get(i).getX();
			hostBulletCords[i][1] = hostBullets.get(i).getY();
		}
		
		for(int i = 0; i < guestBulletCords.length; i++) 
		{
			guestBulletCords[i][0] = guestBullets.get(i).getX();
			guestBulletCords[i][1] = guestBullets.get(i).getY();
		}
	}
	

	public int[][] getHostBulletCords(){return hostBulletCords;}
	public int[][] getGuestBulletCords(){return guestBulletCords;}
	
}
