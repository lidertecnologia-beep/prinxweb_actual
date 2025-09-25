const isFileImage = (file) => file && file.type.split('/')[0] === 'image'

export const typeBlobIsImage = (blob) => blob?.type && blob.type.includes('image')

export const getBlobToFile = (blob, nameFile, mimeType) => blob ? new File([blob], nameFile, { type: mimeType }) : null

export default isFileImage
