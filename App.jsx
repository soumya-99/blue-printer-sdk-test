import {
  View,
  Text,
  Button,
  NativeModules,
  PermissionsAndroid,
  ToastAndroid,
} from 'react-native';
import React, {useEffect, useState} from 'react';
import BleManager from 'react-native-ble-manager';

const App = () => {
  const {MyPrinter} = NativeModules;

  const [isBlueToothEnable, setIsBlueToothEnable] = useState(false);
  async function checkPermissions() {
    try {
      if (Platform.OS === 'android') {
        await PermissionsAndroid.requestMultiple([
          PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
          PermissionsAndroid.PERMISSIONS.ACCESS_COARSE_LOCATION,
          PermissionsAndroid.PERMISSIONS.READ_EXTERNAL_STORAGE,
          PermissionsAndroid.PERMISSIONS.WRITE_EXTERNAL_STORAGE,
          'com.pos.permission.SECURITY',
          'com.pos.permission.ACCESSORY_DATETIME',
          'com.pos.permission.ACCESSORY_LED',
          'com.pos.permission.ACCESSORY_BEEP',
          'com.pos.permission.ACCESSORY_RFREGISTER',
          'com.pos.permission.CARD_READER_ICC',
          'com.pos.permission.CARD_READER_PICC',
          'com.pos.permission.CARD_READER_MAG',
          'com.pos.permission.COMMUNICATION',
          'com.pos.permission.PRINTER',
          'com.pos.permission.ACCESSORY_RFREGISTER',
          'com.pos.permission.EMVCORE',
        ]).then(result => {
          if (
            result['android.permission.ACCESS_COARSE_LOCATION'] &&
            result['android.permission.ACCESS_FINE_LOCATION'] &&
            result['android.permission.READ_EXTERNAL_STORAGE'] &&
            result['android.permission.WRITE_EXTERNAL_STORAGE'] === 'granted'
          ) {
            BleManager.enableBluetooth()
              .then(() => {
                setIsBlueToothEnable(true);
                console.log(
                  'The bluetooth is already enabled or the user confirm',
                );
              })
              .catch(error => {
                console.log('The user refuse to enable bluetooth', error);
              });
          } else if (
            result['android.permission.ACCESS_COARSE_LOCATION'] ||
            result['android.permission.ACCESS_FINE_LOCATION'] ||
            result['android.permission.READ_EXTERNAL_STORAGE'] ||
            result['android.permission.WRITE_EXTERNAL_STORAGE'] ===
              'never_ask_again'
          ) {
            console.log('The user refuse to enable some permission.');
          }
        });
      }
    } catch (error) {
      console.log('Error checking Bluetooth status:', error);
    }
  }

  useEffect(() => {
    checkPermissions();
  }, []);

  const onPress = async () => {
    // try {
    //   MyPrinter.greet('Soumya')
    //     .then(err => console.log('REACT_ERR', err))
    //     .catch(res => console.log(res));
    // } catch (error) {
    //   console.log('React Try-Catch Err', error);
    // }

    // try {
    //   MyPrinter.printText((err, res) => {
    //     if (err) console.log('React Side Err', err);
    //     else console.log('Printing...', res);
    //   });
    // } catch (error) {
    //   console.log('React Try-Catch Err', error);
    // }


    // try {
    //   MyPrinter.printQRCode("Blasakhjdfilusgafysafdeafadef", (err, res) => {
    //     if (err) console.log('React Side Err', err);
    //     else console.log('Printing QR...', res);
    //   });
    // } catch (error) {
    //   console.log('React Try-Catch Err', error);
    // }


    try {
      MyPrinter.centerAlignedPrintText("Blasakhjdfilusgafysafdeafadef\nBlasakhjdfilusgafysafdeafadef\nBlasakhjdfilusgafyseafadef\nBlasakhjdfilusgafysafdeafadef\nBlasakhjdfilusgafysafdeafadef\nBlasakhjdfilusgafysafdeafadef\n\n", 36);
    } catch (error) {
      console.log('React Try-Catch Err', error);
    }
  };

  return (
    <View>
      <Text>Testing Print</Text>
      <Button title={'Print'} onPress={onPress} />
    </View>
  );
};

export default App;
