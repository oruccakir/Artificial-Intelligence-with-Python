import sys
from PyQt5.QtWidgets import QApplication, QMainWindow, QOpenGLWidget
from PyQt5.QtGui import QColor
from OpenGL.GL import *
from OpenGL.GLUT import *


class OpenGLWidget(QOpenGLWidget):
    def initializeGL(self):
        glClearColor(0.0, 0.0, 0.0, 1.0)

    def paintGL(self):
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT)
        glLoadIdentity()

        glColor3f(1.0, 1.0, 1.0)
        glutWireTeapot(0.5)

    def resizeGL(self, width, height):
        glViewport(0, 0, width, height)
        glMatrixMode(GL_PROJECTION)
        glLoadIdentity()
        aspect_ratio = width / height
        gluPerspective(45, aspect_ratio, 0.1, 50.0)
        glMatrixMode(GL_MODELVIEW)


class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()

        self.setWindowTitle("3D Graphic")
        self.setFixedSize(800, 600)

        self.openGLWidget = OpenGLWidget(self)
        self.setCentralWidget(self.openGLWidget)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    mainWindow = MainWindow()
    mainWindow.show()
    sys.exit(app.exec_())
