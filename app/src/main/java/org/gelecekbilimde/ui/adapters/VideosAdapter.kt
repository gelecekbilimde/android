package org.gelecekbilimde.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import org.gelecekbilimde.R
import org.gelecekbilimde.data.entity.Video
import org.gelecekbilimde.databinding.ItemVideoBinding
import org.gelecekbilimde.interfaces.ItemClickListener
import org.gelecekbilimde.util.StringExtension.Companion.toSimpleDate

/**
 *
 * @author ferhatozcelik
 * @since 2023-04-01
 */

class VideosAdapter(var context: Context, var itemClickListener: ItemClickListener, var itemBookmarkClickListener: ItemClickListener) : RecyclerView.Adapter<VideosAdapter.VideoViewHolder>() {

    private var videoList: List<Video> = emptyList()

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(videoList: List<Video>) {
        this.videoList = videoList
        notifyDataSetChanged()
    }

    lateinit var itemVideoBinding: ItemVideoBinding

    class VideoViewHolder(binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemVideoBinding

        init {
            this.binding = binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = videoList[position]

        itemVideoBinding = holder.binding

        itemVideoBinding.apply {
            titleName.text = videoItem.videoTitle ?: ""
            channelName.text = videoItem.channelTitle ?: ""
            videoDate.text = videoItem.videoCreateAtDate?.toSimpleDate() ?: ""
            if (videoItem.videoDescription?.length!! > 50) {
                description.text = (videoItem.videoDescription.substring(1, 50) + "... ")
            } else {
                description.text = videoItem.videoDescription
            }
        }

        Glide.with(context).load(videoItem.videoThumbnailUrl).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(itemVideoBinding.articleImageView)


        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(videoItem)
        }

        itemVideoBinding.bookmarksCardview.setOnClickListener {
            itemBookmarkClickListener.onItemClick(videoItem)
        }

        if (videoItem.isBookmark == true) {
            itemVideoBinding.apply {
                bookmarksCardview.background = ContextCompat.getDrawable(context, R.drawable.view_click_background)
                bookmarksImageView.setColorFilter(ContextCompat.getColor(context, R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
        } else {
            itemVideoBinding.apply {
                bookmarksCardview.background = ContextCompat.getDrawable(context, R.drawable.view_background)
                bookmarksImageView.setColorFilter(ContextCompat.getColor(context, R.color.color_primary), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
        }

    }

    override fun getItemCount(): Int {
        return videoList.size
    }

}

