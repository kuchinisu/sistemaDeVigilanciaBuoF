from PIL import Image
import clip
import torch
import os


modelo = torch.load("model/model.pth")
preprocess = torch.load("model/preprocess.pth")

def prediccionClip(imagenes, propts):
    
    # Procesa las imágenes
    imagenes_procesadas = [preprocess(Image.open(imagen)) for imagen in imagenes]
    
    imagenes_tensor = torch.stack(imagenes_procesadas) 
    
    # Procesa el texto
    text = clip.tokenize([propts])
    
    with torch.no_grad():
        logits_per_image, logits_per_text = modelo(imagenes_tensor, text)

    print(logits_per_image)
    return logits_per_image

imagenesPatths = sorted(os.listdir('src/img/imagennp'))
paths = []
for pa in imagenesPatths:
    paths.append(f"src/img/imagennp/{pa}")

paths

while(True):
    with open("src/recados/usarClip.txt", 'r') as f:
        usar = f.read()
    try:
        if usar == "True":
            usar = True
        elif usar == "False":
            usar = False
        else:
            raise ValueError("el texto en la nota usarClip.txt deve ser 'True' o 'False' ")
        if usar:
            with open("src/recados/propt.txt", 'r') as f:
                propt = f.read()

            predict = prediccionClip(paths, propt)
            m = ""
            for p in predict:
                print(p)
                if p > 25:
                    pred = "y\n"
                    
                else:
                    pred= "n\n"
                m += pred

            with open("src/recados/pred.txt", 'w') as fw:
                    fw.write(f"{m}")
            
    except Exception as e:
        print(f"{e}")



