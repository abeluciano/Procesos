/**
 * ViewHolder para un reporte en un RecyclerView.
 *
 * Autores:
 * Abel Luciano Aragón Alvaro
 * Josshua David Flores Chumbimuni
 * Rodrigo Ojeda Arce
 *
 * Resumen:
 * Esta clase ReporteViewHolder representa una vista de un solo elemento (Reporte) en un
 * RecyclerView. Contiene referencias a las vistas dentro de cada elemento y un método
 * para vincular los datos de un reporte específico a estas vistas.
 */

package com.example.midas.AdapterReporte

import android.content.ClipData
import android.content.ClipboardManager
import android.graphics.Color
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.midas.DatasClass.Reporte
import com.example.midas.DatasClass.Transferencia
import com.example.midas.R

class ReporteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val idReporte = itemView.findViewById<TextView>(R.id.idReporte)
    val typeReport = itemView.findViewById<TextView>(R.id.typeReport)
    val estado = itemView.findViewById<TextView>(R.id.estado)
    val respuesta = itemView.findViewById<TextView>(R.id.respuesta)
    val FechayHora = itemView.findViewById<TextView>(R.id.FechayHora)
    val btnCopiar = itemView.findViewById<ImageButton>(R.id.btnCopiar)

    fun render(item: Reporte) {
        typeReport.text = item.tipoReporte
        idReporte.text = item.idReporte
        respuesta.text = item.respuesta
        if (item.estado == "Solucionado") {
            estado.text = item.estado
            estado.setTextColor(Color.GREEN)
        } else {
            estado.text = item.estado
            estado.setTextColor(Color.RED)
        }
        FechayHora.text = item.FechayHora

        btnCopiar.setOnClickListener(){
            val clipboard = ContextCompat.getSystemService(
                itemView.context,
                ClipboardManager::class.java
            ) as ClipboardManager
            val clip = ClipData.newPlainText("ID", item.idReporte)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(itemView.context, "ID copiado al portapapeles", Toast.LENGTH_SHORT).show()
        }
    }
}