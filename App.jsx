import {View, Text, Button, NativeModules, PermissionsAndroid, ToastAndroid} from 'react-native';
import React, {useState} from 'react';
import BleManager from 'react-native-ble-manager';

const App = () => {
  const {MyPrinter} = NativeModules;

  const [isBlueToothEnable, setIsBlueToothEnable] = useState(false);
  async function checkBluetoothEnabled() {
    try {
      const granted = await PermissionsAndroid.request(
        PermissionsAndroid.PERMISSIONS.ACCESS_FINE_LOCATION,
        {
          title: 'Bluetooth Permission',
          message:
            'This app needs access to your location to check Bluetooth status.',
          buttonNeutral: 'Ask Me Later',
          buttonNegative: 'Cancel',
          buttonPositive: 'OK',
        },
      );
      if (granted === PermissionsAndroid.RESULTS.GRANTED) {
        // Permission granted, check Bluetooth status
        BleManager.enableBluetooth()
          .then(() => {
            // Success code
            setIsBlueToothEnable(true);
            console.log('The bluetooth is already enabled or the user confirm');
          })
          .catch(error => {
            // Failure code
            console.log('The user refuse to enable bluetooth');
          });
        // const isEnabled = await BluetoothStatus.isEnabled();
        // console.log('Bluetooth Enabled:', isEnabled);
      } else {
        console.log('Bluetooth permission denied');
      }
    } catch (error) {
      console.log('Error checking Bluetooth status:', error);
    }
  }

  const onPress = async () => {
    await checkBluetoothEnabled();

    if (!isBlueToothEnable) {
      ToastAndroid.show(
        'please enable the bluetooth first',
        ToastAndroid.SHORT,
      );
      return;
    }
    try {
      MyPrinter.greet('John')
        .then(result => {
          console.log(result);
        })
        .catch(error => {
          console.error(error);
        });
    } catch (error) {
      console.log('eeeeeeeeee', error);
    }

    try {
      // MyPrinter.printText((err, res) => {
      //   if (err) {
      //     console.log("##########$$$$$$$$$$$$$$$", err);
      //   }
      //   console.log("##########$$$$$$$$$$$$$$$", res);
      // });
      MyPrinter.printText().then(res => {
        console.log(res)
      }).catch(err => {
        console.log(err)
      })
    } catch (error) {
      console.log('dshguygtfuydstgfuydufgsdyghi', error);
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
