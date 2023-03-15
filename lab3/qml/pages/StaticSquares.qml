import QtQuick 2.0
import QtQuick.Layouts 1.1
import Sailfish.Silica 1.0

Page {
    objectName: "staticSquares"
    allowedOrientations: Orientation.All
    SilicaFlickable {
        anchors.fill: parent


        PageHeader {
            id : pheader
            objectName: "pageHeader"
            title: qsTr("Квадраты")
            titleColor: "white"
        }

        Item {

            id : props;
            property int size: 150
            property int margs: 50
        }

        Column {

            anchors {
                top: pheader.bottom
                left: parent.left
                leftMargin: props.margs

            }

            Item { // Создание просто квадратов
                id : item1
                implicitHeight: props.size + 2 * props.margs
                Rectangle {
                    y : 0; x : 0;
                    width: props.size; height: props.size;
                    color: "red"
                }
                Rectangle {
                    y : props.size / 2; x : props.size;
                    width: props.size; height: props.size;
                    color: "#45FF17"
                }
                Rectangle {
                    y : 0; x :props.size + props.size / 2;
                    width: props.size; height: props.size;
                    color: "blue"
                    Label {
                        anchors.centerIn: parent;
                        text: qsTr("Квадрат");

                    }
                }
            }
            Item { // Простое решение
                   id: item2;
                   implicitHeight: 2 * props.size + 2 * props.margs
                   anchors.top : item1.bottom

                   Column {
                       spacing: 20
                       Row {
                           spacing: 20
                           Rectangle {
                               y : 0; x : 0;
                               width: props.size; height: props.size;
                               color: "red"
                           }
                           Rectangle {
                               y : 0; x : 0;
                               width: props.size; height: props.size;
                               color: "#45FF17"
                           }
                           Rectangle {
                               y : 0; x : 0;
                               width: props.size; height: props.size;
                               color: "blue"

                           }
                       }
                       Row {
                           spacing: 20
                           Rectangle {
                               y : 0; x : 0;
                               width: props.size; height: props.size;
                               color: "pink"
                           }
                           Rectangle {
                               y : 0; x : 0;
                               width: props.size; height: props.size;
                               color: "white"
                           }
                           Rectangle {
                               y : 0; x : 0;
                               width: props.size; height: props.size;
                               color: "black"

                           }
                       }
                   }

               }
            Item { // Использование Grid
                id: item3

                anchors.top: item2.bottom


                Grid {
                    rows: 2
                    columns: 3

                    spacing: 20

                    Rectangle {
                        y : 0; x : 0;
                        width: props.size; height: props.size;
                        color: "red"
                    }
                    Rectangle {
                        y : 0; x : 0;
                        width: props.size; height: props.size;
                        color: "#45FF17"
                    }
                    Rectangle {
                        y : 0; x : 0;
                        width: props.size; height: props.size;
                        color: "blue"

                    }

                    Rectangle {
                        y : 0; x : 0;
                        width: props.size; height: props.size;
                        color: "pink"
                    }
                    Rectangle {
                        y : 0; x : 0;
                        width: props.size; height: props.size;
                        color: "white"
                    }
                    Rectangle {
                        y : 0; x : 0;
                        width: props.size; height: props.size;
                        color: "black"

                    }
                }
            }
        }
    }
}
