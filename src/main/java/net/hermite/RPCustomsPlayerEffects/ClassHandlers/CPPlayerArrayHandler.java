package net.hermite.RPCustomsPlayerEffects.ClassHandlers;

import java.util.ArrayList;

import net.hermite.RPCustomsPlayerEffects.util.Class.CPPlayer;


public class CPPlayerArrayHandler {
	
	public static void ReplaceOrAdd(CPPlayer p, ArrayList<CPPlayer> pList)
	{
		int ArrayListHKPlayerSize = pList.size();
 		    
 		for(int i=0;i<ArrayListHKPlayerSize;i++)
 		{
 			if (pList.get(i) != null)
 			{
	 			if(pList.get(i).getPlayerId() == p.getPlayerId())
	 		    {
	 		    	pList.remove(i);
	 		    	i = ArrayListHKPlayerSize;
	 		    }
 			}
 		}
 		pList.add(p);
	}
	
	public static int getEffect(int PlayerId, ArrayList<CPPlayer> pList)
	{
		int ArrayListHKPlayerSize = pList.size();
		int action = 4;
 		    
 		for(int i=0;i<ArrayListHKPlayerSize;i++)
 		{
 			if (pList.get(i) != null)
 			{
	 			if(pList.get(i).getPlayerId() == PlayerId)
	 		    {
	 				if(pList.get(i).isWet())
	 				{
	 					return 1;
	 				}
	 				else if (pList.get(i).isSmell())
	 				{
	 					return 2;
	 				}
	 				else if (pList.get(i).isPerfum())
	 				{
	 					return 3;
	 				}
	 				else
	 				{
	 					return 4;
	 				}
	 		    }
 			}
 		}
 		return action;
	}
}
