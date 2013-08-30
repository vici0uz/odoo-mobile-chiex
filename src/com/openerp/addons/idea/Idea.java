/*
 * OpenERP, Open Source Management Solution
 * Copyright (C) 2012-today OpenERP SA (<http://www.openerp.com>)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 * 
 */

package com.openerp.addons.idea;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openerp.R;
import com.openerp.support.BaseFragment;
import com.openerp.support.menu.OEMenu;

// TODO: Auto-generated Javadoc
/**
 * The Class Idea.
 */
public class Idea extends BaseFragment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_idea, container,
				false);
		return rootView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.openerp.support.FragmentHelper#databaseHelper(android.content.Context
	 * )
	 */
	@Override
	public Object databaseHelper(Context context) {
		// TODO Auto-generated method stub
		return new IdeaDBHelper(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.openerp.support.FragmentHelper#handleArguments(android.os.Bundle)
	 */
	@Override
	public void handleArguments(Bundle bundle) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.openerp.support.FragmentHelper#menuHelper(android.content.Context)
	 */
	@Override
	public OEMenu menuHelper(Context context) {
		// TODO Auto-generated method stub
		return null;
	}

}