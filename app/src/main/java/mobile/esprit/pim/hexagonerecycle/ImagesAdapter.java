package mobile.esprit.pim.hexagonerecycle;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abed.hexagonrecyclerview.view.HexagonImageView;

import java.util.List;

import mobile.esprit.pim.R;


public class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.CustomViewHolder> {
    private List<String> images;
    private ViewHolderClicks clicksListener;

    public ImagesAdapter(List<String> images,
                         ViewHolderClicks clicksListener) {
        this.images = images;
        this.clicksListener = clicksListener;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_story, parent, false);
        return new CustomViewHolder(view, clicksListener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        if (images == null || images.size() == 0) {
            return;
        }
        holder.setImageUrl(images.get(position));

    }

    @Override
    public int getItemCount() {
        if (images == null) {
            return 0;
        }

        return images.size();
    }

    public void updateList(List<String> itemsList) {
        this.images = itemsList;
        notifyDataSetChanged();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        HexagonImageView imageView;
        ViewHolderClicks clicksListener;
        String image_url;

        public CustomViewHolder(View view, ViewHolderClicks clicksListener) {
            super(view);
            this.clicksListener = clicksListener;
            imageView = (HexagonImageView) view.findViewById(R.id.img_view);
            imageView.setOnClickListener(this);
        }


        public void setImageUrl(String image_url) {
            this.image_url = image_url;
            imageView.setImageResource(Integer.parseInt(image_url.trim()));

        }

        @Override
        public void onClick(View v) {
            if (clicksListener != null)
                this.clicksListener.onStorySelected(v, getLayoutPosition(), image_url);
        }
    }


    public interface ViewHolderClicks {
        void onStorySelected(View view, int position, String image_url);
    }
}
