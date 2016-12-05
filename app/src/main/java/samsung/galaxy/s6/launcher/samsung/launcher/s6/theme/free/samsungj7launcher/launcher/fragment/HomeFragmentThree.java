package samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.launcher.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import samsung.galaxy.s6.launcher.samsung.launcher.s6.theme.free.samsungj7launcher.R;


/**
 * Created by iamla on 11/29/2016.
 */
public class HomeFragmentThree extends Fragment {
    public ViewGroup rootView;

    public static HomeFragmentThree create(int pageNumber) {
        HomeFragmentThree fragment = new HomeFragmentThree();
        fragment.setArguments(new Bundle());
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.rootView = (ViewGroup) inflater.inflate(R.layout.home_layout_three, container, false);
        return this.rootView;
    }

    public void onDestroyView() {
        super.onDestroyView();
    }
}

