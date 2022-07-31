package net.flow9.thisiskotlin.econg

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import net.flow9.thisiskotlin.econg.databinding.ItemItemsBinding
import net.flow9.thisiskotlin.econg.data.Memo

class CustomAdapter: RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<Memo>()//어댑터에서 사용할 목록변수
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {//한 화면에 생성할 레이아웃 개수 = 한 화면에 생성할 아이템 개수-> 아이템 레이아웃 생성
        val binding = ItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)//여기서 만들어 놓은 item_recycler를 붙이는 건가? 그런듯.
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {//아이템 레이아웃에 값 입력 후 출력 -> 생성된 뷰 홀더를 보여줌.
        val memo = listData.get(position)
        holder.setMemo(memo)
    }

    override fun getItemCount(): Int {// 목록에 나오는 아이템 개수
        return listData.size
    }
}
class Holder(val binding: ItemItemsBinding): RecyclerView.ViewHolder(binding.root){
    init{
        binding.root.setOnClickListener {
            Toast.makeText(binding.root.context, "클릭된 아이템 = ${binding.subName.text}", Toast.LENGTH_LONG).show()//클릭 이벤트
        }
    }
    fun setMemo(memo: Memo) {
        binding.subName.text = "${memo.title}"
        binding.subInfo.text = "${memo.info}"
    }

}