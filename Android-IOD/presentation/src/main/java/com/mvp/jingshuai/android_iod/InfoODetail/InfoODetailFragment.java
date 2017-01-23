package com.mvp.jingshuai.android_iod.InfoODetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mvp.jingshuai.android_iod.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InfoODetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InfoODetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoODetailFragment extends Fragment implements InfoODetailContract.View {

    private InfoODetailContract.Presenter mPresenter;

    private TextView mDetailAttribute;
    private TextView mDetailCategory;
    private TextView mDetailViewCount;
    private TextView mDetailName;
    private TextView mDetailDescription;
    private ImageView mInfoImageView;
    @NonNull
    private static final String ARGUMENT_TASK_ID = "TASK_ID";



    public static InfoODetailFragment newInstance(@Nullable String taskId) {
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_TASK_ID, taskId);
        InfoODetailFragment fragment = new InfoODetailFragment();
        fragment.setArguments(arguments);
        return fragment;
    }


    @Override
    public void showInfoImage(String imageUrl) {
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(mInfoImageView);
    }

    @Override
    public void setLoadingIndicator(boolean active) {

    }

    @Override
    public void hideTitle() {

    }

    @Override
    public void showTitle(String title) {
        mDetailName.setText(title);
    }

    @Override
    public void hideAttribute() {

    }

    @Override
    public void showAttribute(String attribute) {
        mDetailAttribute.setText(attribute);

    }

    @Override
    public void hideCategory() {

    }

    @Override
    public void showCategory(String category) {
        mDetailCategory.setText(category);
    }

    @Override
    public void hideViewCount() {

    }

    @Override
    public void showViewCount(String viewCount) {
        mDetailViewCount.setText(viewCount);
    }

    @Override
    public void hideDescription() {

    }

    @Override
    public void showDescription(String description) {
        mDetailDescription.setText(description);
    }

    @Override
    public boolean isActive() {
        return false;
    }



    public InfoODetailFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =inflater.inflate(R.layout.fragment_info_odetail, container, false);
        mDetailAttribute = (TextView) root.findViewById(R.id.tv_attribute);
        mDetailCategory = (TextView) root.findViewById(R.id.tv_category);
        mDetailViewCount = (TextView) root.findViewById(R.id.tv_viewcount);
        mDetailName = (TextView) root.findViewById(R.id.tv_fullname);
        mDetailDescription = (TextView) root.findViewById(R.id.tv_description);
        mInfoImageView = (ImageView) root.findViewById(R.id.im_infoo);
        return root;
    }

    @Override
    public void setPresenter(InfoODetailContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
