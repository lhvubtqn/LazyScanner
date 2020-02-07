package com.github.lhvubtqn.lazyscanner.ui.files;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.lhvubtqn.lazyscanner.R;

import java.util.ArrayList;
import java.util.Arrays;

public class FilesFragment extends Fragment {

    private ArrayList<File> files;
    private ListView listView;
    private View root;

    private File[] mockup = new File[]{new File("a", "a", "a"), new File("b", "b", "b")};

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        root = inflater.inflate(R.layout.fragment_files, container, false);

        listView = root.findViewById(R.id.list_files);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Open editor
            }
        });
        listView.setAdapter(new FilesAdapter(getActivity(), R.layout.list_files_item, Arrays.asList(mockup)));
        registerForContextMenu(listView);
        return root;
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.menu_edit_file, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.delete_item_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.item_delete:
                // do delete item
        }
        return super.onContextItemSelected(item);
    }
}