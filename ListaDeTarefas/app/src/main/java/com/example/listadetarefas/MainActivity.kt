package com.example.listadetarefas

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Verifica se a permissão BLUETOOTH_CONNECT foi concedida
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, pode continuar com a funcionalidade
                setupBluetooth()
            } else {
                // Permissão não concedida, solicita permissão
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.BLUETOOTH_CONNECT), 1)
            }
        } else {

            setupBluetooth()
        }
    }

    private fun setupBluetooth() {
        try {
            val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()

            if (bluetoothAdapter == null) {
                // Caso o dispositivo não tenha Bluetooth
                Toast.makeText(this, "Este dispositivo não possui Bluetooth", Toast.LENGTH_SHORT).show()
            } else {
                if (!bluetoothAdapter.isEnabled) {
                    // Se o Bluetooth não está ativado, solicita ao usuário para ativá-lo
                    val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                    startActivityForResult(enableBtIntent, 1)
                } else {
                    // Se o Bluetooth já está ativado, redireciona para as configurações
                    showBluetoothStatus(true)
                    redirectToBluetoothSettings()
                }
            }
        } catch (e: SecurityException) {
            // Caso ocorra um erro de permissão ao tentar acessar o Bluetooth
            Toast.makeText(this, "Permissão de Bluetooth necessária", Toast.LENGTH_SHORT).show()
        }
    }

    private fun redirectToBluetoothSettings() {
        val intent = Intent(Settings.ACTION_BLUETOOTH_SETTINGS).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            putExtra("EXTRA_CONNECTION_ONLY", true)
            putExtra("EXTRA_CLOSE_ON_CONNECT", true)
            putExtra("android.bluetooth.devicepicker.extra.FILTER_TYPE", 1)
        }
        startActivity(intent)
    }

    // Função para mostrar o status do Bluetooth com Toast
    private fun showBluetoothStatus(isEnabled: Boolean) {
        val message = if (isEnabled) {
            "Bluetooth ativado!"
        } else {
            "Bluetooth não ativado. Por favor, ative o Bluetooth."
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Sobrescrevendo o método para tratar a resposta da solicitação de permissões
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permissão concedida, agora pode prosseguir com a funcionalidade
                setupBluetooth()
            } else {
                // Permissão negada, informe ao usuário
                Toast.makeText(this, "Permissão de Bluetooth negada", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
