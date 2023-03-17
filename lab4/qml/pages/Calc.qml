import QtQuick 2.0
import QtQuick.Layouts 1.1
import Sailfish.Silica 1.0

Page {
    objectName: "mainPage"
    allowedOrientations: Orientation.All
    SilicaFlickable {
        anchors.fill: parent

        PageHeader {
            id : pheader
            objectName: "pageHeader"
            title: qsTr("КАЛЬКУЛЯТОР")
            titleColor: "white"
        }
        Grid {
            width: parent.width
            anchors {
                left:parent.left
                top: pheader.bottom
                margins: 50

            }
            rows: 2
            columns: 2
            spacing: 20

            TextField {
                id : field1
                implicitWidth: parent.width / 2 - 50
                placeholderText: qsTr("")
            }
            TextField {
                id : field2
                implicitWidth: parent.width / 2 - 50
                placeholderText: qsTr("")
            }
            Button {
                text: "Отменить"
                onClicked: {
                    field1.text = ""
                    field2.text = ""
                }

                backgroundColor: "white"
                color: "black"
            }
            Button {
                text: "Сложить"
                onClicked: {
                    console.log(
                        parseInt(field1.text) + parseInt(field2.text)
                                );
                }

                backgroundColor: "white"
                color: "black"
            }

        }


    }
}
