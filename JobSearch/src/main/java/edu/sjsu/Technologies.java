package edu.sjsu;

import java.util.ArrayList;

public class Technologies {
	private ArrayList<Technology> items = new ArrayList<Technology>();
	private boolean has_more;
	private int quota_max;
	private int quota_remaining;
	
	public void setItems(ArrayList<Technology> items) {
		this.items = items;
	}

	public void setHas_more(boolean has_more) {
		this.has_more = has_more;
	}

	public void setQuota_max(int quota_max) {
		this.quota_max = quota_max;
	}

	public void setQuota_remaining(int quota_remaining) {
		this.quota_remaining = quota_remaining;
	}	
	
	public ArrayList<Technology> getItems() {
		return items;
	}
	
	public boolean getHas_more() {
        return has_more;
    }
	
	public int getQuota_max() {
        return quota_max;
    }
	
	public int getQuota_remaining() {
        return quota_remaining;
    }
}
