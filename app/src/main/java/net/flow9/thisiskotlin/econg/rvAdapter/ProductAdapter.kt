package net.flow9.thisiskotlin.econg.rvAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import net.flow9.thisiskotlin.econg.DetailFundActivity
import net.flow9.thisiskotlin.econg.DetailPurActivity
import net.flow9.thisiskotlin.econg.data.Memo
import net.flow9.thisiskotlin.econg.databinding.ItemItemsBinding
//상품
class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ProductHolder>() {
    var listData = mutableListOf<Memo>()//어댑터에서 사용할 목록변수
    var context: Context? = null
    var listener: ProductAdapter.OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {//한 화면에 생성할 레이아웃 개수 = 한 화면에 생성할 아이템 개수-> 아이템 레이아웃 생성
        context = parent.context
        val binding = ItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)//여기서 만들어 놓은 item_recycler를 붙이는 건가? 그런듯.
        return ProductHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {//아이템 레이아웃에 값 입력 후 출력 -> 생성된 뷰 홀더를 보여줌.
        val memo = listData.get(position)
        holder.setMemo(memo)

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(memo.title)
        }
    }

    override fun getItemCount(): Int {// 목록에 나오는 아이템 개수
        return listData.size
    }

    fun setData(arrData : MutableList<Memo>){
        listData = arrData as ArrayList<Memo>
    }

    fun setClickListener(listener1: ProductAdapter.OnItemClickListener){
        listener = listener1
    }

    inner class ProductHolder(val binding: ItemItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun setMemo(memo: Memo) {
            binding.subName.text = "${memo.title}"
            binding.subInfo.text = "${memo.info}"
        }

    }

    interface OnItemClickListener{
        fun onClicked(id:String)
    }
}
