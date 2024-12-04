import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appcolegio.R
import com.google.firebase.auth.FirebaseUser

class AlumnosAdapter(
    private val alumnos: List<FirebaseUser>,
    private val onDelete: (FirebaseUser) -> Unit
) : RecyclerView.Adapter<AlumnosAdapter.AlumnoViewHolder>() {

    class AlumnoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val emailTextView: TextView = view.findViewById(R.id.tvEmail)
        val deleteButton: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlumnoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alumno, parent, false)
        return AlumnoViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlumnoViewHolder, position: Int) {
        val alumno = alumnos[position]
        holder.emailTextView.text = alumno.email
        holder.deleteButton.setOnClickListener {
            onDelete(alumno)
        }
    }

    override fun getItemCount() = alumnos.size
}
