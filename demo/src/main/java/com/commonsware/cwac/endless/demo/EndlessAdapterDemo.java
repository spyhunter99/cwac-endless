/***
 * Copyright (c) 2008-2009 CommonsWare, LLC
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.commonsware.cwac.endless.demo;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import java.util.ArrayList;

public class EndlessAdapterDemo extends ListActivity {
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);

        final SwipeRefreshLayout sr = (SwipeRefreshLayout) findViewById(R.id.layout_main_swiperefresh);
        sr.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                sr.setRefreshing(true);
                renewContent();
                sr.setRefreshing(false);
            }
        });
        refreshContent();
    }

    private void renewContent() {
        DemoAdapter adapter = null;
        ArrayList<Integer> items = new ArrayList<Integer>();

        for (int i = 0; i < 25; i++) {
            items.add(i);
        }

        adapter = new DemoAdapter(this, items);
        setListAdapter(adapter);
    }

    private void refreshContent() {
        DemoAdapter adapter = (DemoAdapter) getLastNonConfigurationInstance();

        if (adapter == null) {
            ArrayList<Integer> items = new ArrayList<Integer>();

            for (int i = 0; i < 25; i++) {
                items.add(i);
            }

            adapter = new DemoAdapter(this, items);
        } else {

            adapter.startProgressAnimation();
        }

        setListAdapter(adapter);
    }

    @Override
    public Object getLastNonConfigurationInstance() {
        return (getListAdapter());
    }
}
