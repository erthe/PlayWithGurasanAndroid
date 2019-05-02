package com.aochdjp.playwithgura_san.model.util

import android.support.v7.util.DiffUtil

class DiffUtilCallback(private val old: List<*>?, private val new: List<*>?) : DiffUtil.Callback() {

    /**
     * 同じアイテムか？
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) = old?.getOrNull(oldItemPosition) == new?.getOrNull(newItemPosition)

    /**
     * 更新される前のリストのサイズ
     */
    override fun getOldListSize() = old?.size ?: 0

    /**
     * 更新されたリストのサイズ
     */
    override fun getNewListSize() = new?.size ?: 0

    /**
     * アイテムの中身が同じか？
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) = old?.getOrNull(oldItemPosition) == new?.getOrNull(newItemPosition)
}