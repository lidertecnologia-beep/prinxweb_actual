export const getCreateSVGAddImg = (heightValue, widthValue, viewBoxValue, urlImg) => {
  if (typeof document !== 'undefined') {
    const $svgEl = document.createElementNS('http://www.w3.org/2000/svg', 'svg')
    const $imgSVG = document.createElementNS('http://www.w3.org/2000/svg', 'image')
    $svgEl.setAttribute('height', heightValue)
    $svgEl.setAttribute('width', widthValue)
    if (viewBoxValue)$svgEl.setAttribute('viewBox', viewBoxValue)
    $imgSVG.setAttributeNS('http://www.w3.org/1999/xlink', 'href', urlImg)
    $svgEl.appendChild($imgSVG)
    return $svgEl
  }
  return null
}

const getCreateEl = (type, ccsClass) => {
  if (typeof document !== 'undefined' && type) {
    const $el = document.createElement(type)
    if (ccsClass) {
      $el.className = ccsClass
    }
    return $el
  }
  return null
}

export default getCreateEl
