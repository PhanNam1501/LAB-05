package hust.soict.cybersec.aims.media;

import java.util.ArrayList;

import hust.soict.cybersec.aims.exception.PlayerException;

public class CompactDisc extends Disc implements Playable {
   private String artist;
   private ArrayList<Track> tracks = new ArrayList<Track>();

   public String getArtist() {
      return artist;
   }

   public CompactDisc(){}

   public CompactDisc(String title) {
      super(title);
   }

   public CompactDisc(String title, float cost) {
      super(title, cost);
   }

   public CompactDisc(String title, String category, float cost) {
      super(title, category, cost);
   }

   public CompactDisc(String title, String category, String director, float cost) {
      super(title, category, director, cost);
   }

   public CompactDisc(String title, String category, String director, float cost, int length) {
      super(title, category, director, length, cost);
   }
   
   public CompactDisc(String title, String category, String director, String artist, int length, float cost) {
      super(title, category, director, length, cost);
      this.artist = artist;
   }
   
   public void addTrack(Track track) {
      if (tracks.contains(track)) {
         System.out.println("Track already exists");
      } else {
         tracks.add(track);
      }
   }

   public void removeTrack(Track track) {
      if (tracks.contains(track)) {
         tracks.remove(track);
      } else {
         System.out.println("Track not found");
      }
   }

   public int getLength() {
      int res = 0;
      for (Track track : tracks) {
         res += track.getLength();
      }
      return res;
   }

   public void play() throws PlayerException {
	   if(this.getLength() > 0) {
		// TODO Play all tracks in the CD as you have implemented
		java.util.Iterator iter = tracks.iterator();
		Track nextTrack;
		while(iter.hasNext()) {
		nextTrack = (Track) iter.next();
		try {
		nextTrack.play();
		}catch(PlayerException e) {
		throw e;
		}
		}
		}else {
		throw new PlayerException("ERROR: CD length is non-positive!");
		}
	}
   
}