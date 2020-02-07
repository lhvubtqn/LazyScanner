package com.github.lhvubtqn.lazyscanner.ui.files;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.lhvubtqn.lazyscanner.R;

import java.util.List;

public class FilesAdapter extends ArrayAdapter<File> {
    private Activity context;
    private int resource;
    private List<File> files;

    public FilesAdapter(@NonNull Activity context, int resource, @NonNull List<File> files) {
        super(context, resource, files);
        this.context = context;
        this.resource = resource;
        this.files = files;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View row, @NonNull ViewGroup parent) {
        if (row == null) {
            LayoutInflater layoutInflater = this.context.getLayoutInflater();
            row = layoutInflater.inflate(this.resource, parent, false);
        }

        TextView txtTitle = row.findViewById(R.id.txtTitle);
        TextView txtCreatedAt = row.findViewById(R.id.txtCreatedAt);
        final File item = this.files.get(position);
        txtTitle.setText(item.getTitle());
        txtCreatedAt.setText(item.getCreated_at());
        return row;
    }
}
