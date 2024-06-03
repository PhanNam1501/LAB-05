
package hust.soict.cybersec.aims.media;

import java.util.Comparator;

public class Media {
   private int id;
   private String title;
   private String category;
   private float cost;
   
   
   public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();
   public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();


   @Override
   public boolean equals(Object obj) {
       if (this == obj) {
           return true;
       }
       if (obj == null) {
           return false;
       }
       if (!(obj instanceof Media)) {
           return false;
       }
       Media other = (Media) obj;
       if (this.title == null) {
           if (other.title != null) {
               return false;
           }
       } else if (!this.title.equals(other.title)) {
           return false;
       }
       return true;
   }
   
   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public float getCost() {
      return cost;
   }

   public void setCost(float cost) {
      this.cost = cost;
   }

   public Media() {
   }

   public Media(String title) {
      this.title = title;
   }

   public Media(String title, float cost) {
      this.title = title;
      this.cost = cost;
   }

   public Media(String title, String category, float cost) {
      this.title = title;
      this.cost = cost;
      this.category = category;
   }
   
   public Media(String title, String category, float cost, int i) {
	  this.id = id;
	  this.title = title;
	  this.cost = cost;
	  this.category = category;
}
  


}
