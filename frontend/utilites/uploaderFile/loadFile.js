import getCreateEl, { getCreateSVGAddImg } from "../createElementDOM";
import isFileImage from "../files";

export const progressUpload = (file) => {
  const $progress = getCreateEl("progress");

  $progress.value = 0;
  $progress.max = 100;

  const fileReader = new FileReader();
  fileReader.readAsDataURL(file);

  fileReader.addEventListener("progress", (e) => {
    $progress.value = parseInt((e.loaded * 100) / e.total);
  });

  fileReader.addEventListener("loadend", () => {});

  return $progress;
};


const getProgress = () => {
  const $progress = getCreateEl("progress");
  $progress.value = 0;
  $progress.max = 100;
  return $progress;
};

const createElImgSrcView = (file, classCss) => {
  const $imgEl = getCreateEl("img", classCss);
  $imgEl.src = URL.createObjectURL(file);
  return $imgEl;
};

const createViewLoadFile = (file) => {
  // logica de div con el archivo ya cargado y si es una imagen
  const booIsFileImage = isFileImage(file);
  if (file && booIsFileImage) {
    const $elDivLoadFile = getCreateEl("div", "previewLoadFile__load");
    const $elDivImageLoadFile = getCreateEl(
      "div",
      "previewLoadFile__load--img"
    );
    $elDivImageLoadFile.insertAdjacentElement(
      "beforeend",
      createElImgSrcView(file, null)
    );
    $elDivLoadFile.insertAdjacentElement("beforeend", $elDivImageLoadFile);
    return $elDivLoadFile;
  }
  return null;
};

const creatDropDownMenu = () => {
  const $elDivMenu = getCreateEl("div", "previewLoadFile__dropdownmenu");
  const $elLinkMenuItemDowload = getCreateEl(
    "a",
    "previewLoadFile__dropdownmenu--link"
  );
  const $elLinkMenuItemDelete = getCreateEl(
    "a",
    "previewLoadFile__dropdownmenu--link"
  );
  const $elSpanMenuItemDelete = getCreateEl(
    "span",
    "previewLoadFile__dropdownmenu--text"
  );
  const $elSpanMenuItemDowload = getCreateEl(
    "span",
    "previewLoadFile__dropdownmenu--text"
  );
  $elSpanMenuItemDowload.textContent = "Descargar archivo";
  $elSpanMenuItemDelete.textContent = "Eliminar archivo";
  $elLinkMenuItemDowload.insertAdjacentElement(
    "beforeend",
    $elSpanMenuItemDowload
  );
  $elLinkMenuItemDelete.insertAdjacentElement(
    "beforeend",
    $elSpanMenuItemDelete
  );
  $elDivMenu.insertAdjacentElement("beforeend", $elLinkMenuItemDowload);
  $elDivMenu.insertAdjacentElement("beforeend", $elLinkMenuItemDelete);
  return $elDivMenu;
};

const createPreviewLoadFile = (filesIn, divContainer) => {
  if (filesIn && divContainer) {
    const files = Array.from(filesIn);
    const $divContainer = divContainer;
    files.forEach((file) => {
      const $elDivLoad = getCreateEl("div", "previewLoadFile__loader-progress");

      if ($elDivLoad) {
        const fileReader = new FileReader();
        fileReader.readAsDataURL(file);

        // logica de carga del div con el progreso de carga y preview imagen o icono
        const $progress = getProgress();
        const $divLoadImageNoIcon = getCreateEl("div");
        const $divLoadContent = getCreateEl("div");
        const svgElIconNoImg = getCreateSVGAddImg(
          "auto",
          "100%",
          "-40 -40 193 230",
          "http://localhost:3000/img/file-alt-regular.svg"
        );
        const $divLoadSpanNameFile = getCreateEl(
          "span",
          "previewLoadFile__text"
        );
        const $divOverlay = getCreateEl("div", "previewLoadFile__overlay");
        const $divMenu = creatDropDownMenu();
        const $divIconButtonList = getCreateEl(
          "div",
          "previewLoadFile__iconbutton"
        );
        const svgElIconIconButtonList = getCreateSVGAddImg(
          "auto",
          "100%",
          "-40 -40 193 230",
          "http://localhost:3000/img/chevron-down-solid.svg"
        );
        $divIconButtonList.setAttribute("id", "iconButton");
        $divLoadSpanNameFile.innerHTML =
          file.name.length <= 25
            ? file.name
            : `${file.name.substring(0, 30)}...`;
        $divLoadImageNoIcon.className = "previewLoadFile__loader-progress--img";
        $divLoadContent.className = "previewLoadFile__loader-progress--content";
        $divLoadContent.insertAdjacentElement(
          "beforeend",
          $divLoadSpanNameFile
        );
        $divLoadContent.insertAdjacentElement("beforeend", $progress);
        $divLoadImageNoIcon.insertAdjacentElement("beforeend", svgElIconNoImg);
        $divIconButtonList.insertAdjacentElement(
          "beforeend",
          svgElIconIconButtonList
        );
        $divIconButtonList.insertAdjacentElement("beforeend", $divMenu);
        $elDivLoad.insertAdjacentElement("beforeend", $divLoadImageNoIcon);
        $elDivLoad.insertAdjacentElement("beforeend", $divLoadContent);
        $elDivLoad.insertAdjacentElement("beforeend", $divOverlay);
        $elDivLoad.insertAdjacentElement("beforeend", $divIconButtonList);
        $divContainer.insertAdjacentElement("beforeend", $elDivLoad);

        fileReader.addEventListener("progress", (e) => {
          $progress.value = parseInt((e.loaded * 100) / e.total);
        });

        fileReader.addEventListener("loadend", () => {
          setTimeout(() => {
            const $elDivImgLoadFile = createViewLoadFile(file);

            // cuando el archivo es un imagen preview de carga
            if ($elDivImgLoadFile) {
              // Se remueve el div  de progreso de carga y preview de imagen
              $divContainer.removeChild($elDivLoad);
              // logica de div con el archivo ya cargado
              $elDivImgLoadFile.insertAdjacentElement("beforeend", $divOverlay);
              $divIconButtonList.insertAdjacentElement(
                "beforeend",
                svgElIconIconButtonList
              );
              $elDivImgLoadFile.insertAdjacentElement(
                "beforeend",
                $divIconButtonList
              );
              $divContainer.insertAdjacentElement(
                "beforeend",
                $elDivImgLoadFile
              );
            }

            $divIconButtonList.addEventListener("click", () => {
              $divMenu.classList.toggle("previewLoadFile__dropdownmenu--show");
            });

            // $divContainer.insertAdjacentElement("beforeend", $divOverlay)
          }, 2000);
        });
      }
    });
  }
};

export default createPreviewLoadFile;
