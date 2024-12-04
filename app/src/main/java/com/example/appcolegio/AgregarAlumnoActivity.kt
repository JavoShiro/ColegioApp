import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcolegio.databinding.ActivityCrearAlumnoBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AgregarAlumnoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCrearAlumnoBinding
    private lateinit var auth: FirebaseAuth
    private val alumnosList = mutableListOf<FirebaseUser>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCrearAlumnoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        // Configuración de RecyclerView
        binding.rvAlumnos.layoutManager = LinearLayoutManager(this)
        val adapter = AlumnosAdapter(alumnosList) { userToDelete ->
            eliminarCuenta(userToDelete)
        }
        binding.rvAlumnos.adapter = adapter

        // Botón para crear cuenta
        binding.btnCrearCuenta.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (!email.endsWith(".alum@colegio.com")) {
                Toast.makeText(this, "El correo debe terminar con '.alum@colegio.com'", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Cuenta creada con éxito", Toast.LENGTH_SHORT).show()
                    } else {
                        val error = (task.exception as? FirebaseAuthException)?.message
                        Toast.makeText(this, "Error: $error", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Botón para mostrar/eliminar cuentas
        binding.btnEliminarCuenta.setOnClickListener {
            binding.rvAlumnos.visibility =
                if (binding.rvAlumnos.visibility == View.GONE) View.VISIBLE else View.GONE
            cargarCuentasAlumnos(adapter)
        }
    }

    private fun cargarCuentasAlumnos(adapter: AlumnosAdapter) {
        // Cargar las cuentas (requiere configuración adicional de Firebase Admin SDK)
        // Aquí se simula con cuentas de usuario actual (por restricciones de FirebaseAuth estándar)
        val currentUser = auth.currentUser
        if (currentUser != null && currentUser.email?.endsWith(".alum@colegio.com") == true) {
            alumnosList.clear()
            alumnosList.add(currentUser)
            adapter.notifyDataSetChanged()
        }
    }

    private fun eliminarCuenta(userToDelete: FirebaseUser) {
        userToDelete.delete()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Cuenta eliminada con éxito", Toast.LENGTH_SHORT).show()
                    alumnosList.remove(userToDelete)
                } else {
                    val error = (task.exception as? FirebaseAuthException)?.message
                    Toast.makeText(this, "Error al eliminar: $error", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
