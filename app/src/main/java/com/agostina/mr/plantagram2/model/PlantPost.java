package com.agostina.mr.plantagram2.model;

import android.media.metrics.PlaybackMetrics;

public class PlantPost {
  private Plant plant;
    private User user;

    public PlantPost(Plant plant, User user) {
      this.plant = plant;
        this. user = user;
    }

  public Plant getPlant() {
    return plant;
  }

  public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
