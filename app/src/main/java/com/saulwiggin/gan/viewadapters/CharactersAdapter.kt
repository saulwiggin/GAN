package com.saulwiggin.gan.viewadapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saulwiggin.gan.R
import com.saulwiggin.gan.database.BreakingBadCharacter
import kotlinx.android.synthetic.main.fragment_second.view.*
import com.saulwiggin.gan.databinding.RowCharacterBinding

class CharactersAdapter(
    private val clickListener: CharacterClick
) : RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    var results: List<BreakingBadCharacter> = emptyList()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val withDataBinding: RowCharacterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ViewHolder.LAYOUT,
            parent,
            false
        )
        return ViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.results = results[position]
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }

    class ViewHolder(val viewDataBinding: RowCharacterBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        fun bind(character: BreakingBadCharacter) {
            itemView.tv_name.text = character.name
            Glide.with(itemView.iv_character).load(character.img).into(itemView.iv_character)
        }
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.row_character
        }
    }

}

class CharacterClick(val block: (BreakingBadCharacter) -> Unit) {

    fun onClick(character: BreakingBadCharacter) = block(character)
}
