/* eslint-disable import/no-anonymous-default-export */
import mime from 'mime-types'

export default (req, res) => {
  const { extension } = req.query
  const mimeType = mime.lookup(extension)
  res.status(200).json({ mimeType })
}
