package com.rand42.presenters;
import com.rand42.model.IModel;
import com.rand42.model.LocalModel;

public class AdminPresenter {

	private IModel model;
	
	public AdminPresenter(){
		model = LocalModel.getModel();
	}
	
	
	
}
