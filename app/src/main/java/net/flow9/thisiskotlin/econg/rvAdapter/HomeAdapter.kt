package net.flow9.thisiskotlin.econg.rvAdapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import net.flow9.thisiskotlin.econg.data.HomeData
import net.flow9.thisiskotlin.econg.databinding.ItemItemsBinding

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeHolder>() {
    var listData = mutableListOf<HomeData>()//어댑터에서 사용할 목록변수
    var context: Context? = null
    var listener: HomeAdapter.OnItemClickListener? = null
    val storage = Firebase.storage("gs://econg-7e3f6.appspot.com")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {//한 화면에 생성할 레이아웃 개수 = 한 화면에 생성할 아이템 개수-> 아이템 레이아웃 생성
        context = parent.context
        val binding = ItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)//여기서 만들어 놓은 item_recycler를 붙이는 건가? 그런듯.
        return HomeHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {//아이템 레이아웃에 값 입력 후 출력 -> 생성된 뷰 홀더를 보여줌.
        val data = listData.get(position)
        holder.setData(data)

        holder.itemView.rootView.setOnClickListener {
            listener!!.onClicked(data.id.toString(), data.productType)
        }
    }

    override fun getItemCount(): Int {// 목록에 나오는 아이템 개수
        return listData.size
    }

    fun setData(arrData : MutableList<HomeData>?){
        listData = arrData as ArrayList<HomeData>
    }

    fun setClickListener(listener1: HomeAdapter.OnItemClickListener){
        listener = listener1
    }

    inner class HomeHolder(val binding: ItemItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun setData(data: HomeData) {
            binding.subName.text = "${data.title}"
            binding.subInfo.text = "${data.price}"
            binding.subInfo2.text = "${data.companyName}"
            storage.getReferenceFromUrl(data.imgUrl).downloadUrl.addOnSuccessListener { uri ->
                Glide.with(binding.imgSub).load(uri).into(binding.imgSub)
            }.addOnFailureListener {
                Log.e("STORAGE", "DOWNLOAD_ERROR=>${it.message}")
            }
        }

    }

    interface OnItemClickListener{
        fun onClicked(id:String, productType: String)
    }
}